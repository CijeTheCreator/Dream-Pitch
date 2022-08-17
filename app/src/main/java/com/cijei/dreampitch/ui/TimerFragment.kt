package com.cijei.dreampitch.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cijei.dreampitch.R
import com.cijei.dreampitch.data.Game
import com.cijei.dreampitch.data.Goal
import com.cijei.dreampitch.data.Set
import com.cijei.dreampitch.hood.MinuteCountDown
import com.cijei.dreampitch.hood.SecondCountDown
import com.cijei.dreampitch.mock.MockPlayers
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDateTime
import java.time.MonthDay.now
import java.time.ZoneId
import java.util.*

class TimerFragment(val home: Set, val away: Set): Fragment() {

    //TODO("Should take in 2 teams before starting, the 2 teams would create a game")
    //TODO("If the countdown ends or is ended, the goal scorer and the assistor should be asked for and recorded")
    private var tempPauseMinuteData: Int = 0
    private var tempPauseSecondData: Int = 0
    private var isPaused = false
    lateinit var minuteCountDown: MinuteCountDown
    private lateinit var secondCountDown: SecondCountDown
    private var pause: Boolean = false
    private var countdownduration: Long = 2
    private var goalCount: Int = 0

    private var winningTeam: Int = 300
    private var scorer: Int = 300

    private lateinit var database: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_timer_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        startCountDown(view, countdownduration)
        database = FirebaseDatabase.getInstance("https://dream-pitch-default-rtdb.firebaseio.com/").getReference("Games")
    }

    private fun startCountDown(view: View, minutes: Long, secondsCount: Int = 0, minutesCount: Int = 0) {
        val minutesToMilliSeconds = minutes * 60 * 1000
        val minuteTextView = view.findViewById<TextView>(R.id.minuteTextView)
        val secondTextView = view.findViewById<TextView>(R.id.secondTextView)
        secondCountDown = SecondCountDown(60000, 1000, secondTextView) as SecondCountDown
        minuteCountDown = MinuteCountDown(minutesToMilliSeconds, 60000, minuteTextView, 0, this.context, secondCountDown)
        minuteCountDown.count = minutesCount
        minuteCountDown.secondCountDown.count = secondsCount

        minuteCountDown.start()
        stopCountDown(view, minuteCountDown)
        pauseCountDown(view, minuteCountDown)
    }

    private fun stopCountDown(view: View, countdown: MinuteCountDown) {
        val stopButton = view.findViewById<Button>(R.id.stop_button)
        val minuteTextView = view.findViewById<TextView>(R.id.minuteTextView)
        val secondTextView = view.findViewById<TextView>(R.id.secondTextView)
        stopButton.setOnClickListener {
            if (!pause) {
                goalCount = countdown.count
                countdown.cancel()
                countdown.secondCountDown.cancel()
                countdown.count = 0
                countdown.secondCountDown.count = 0
            } else {
                goalCount = countdown.count
                secondCountDown.cancel()
                secondCountDown.count = 0
                minuteCountDown.cancel()
                minuteCountDown.secondCountDown.cancel()
                minuteCountDown.count = 0
                minuteCountDown.secondCountDown.count = 0

            }
            minuteTextView.text = "00"
            secondTextView.text = "00"

            //Convert to function later
            val alertDialog = AlertDialog.Builder(this.context)
            var winningTeam = 3
            alertDialog.setTitle("Who Won?")
            val whoWonBooleanArray = booleanArrayOf(false, false)
            val whoWonTeamsArray = arrayOf(home.teamName, away.teamName)
            alertDialog.setMultiChoiceItems(whoWonTeamsArray, whoWonBooleanArray) { dialog, which, isChecked ->
                if (isChecked) {
                    winningTeam = which
                } else if(winningTeam == which) {
                    winningTeam = 300
                }
            }.setPositiveButton ("Next") {dialog, id ->
                if (winningTeam != 300) {
                    val playerAlertDialog = AlertDialog.Builder(this.context)
                    playerAlertDialog.setTitle("Who Scored?")
                    val teams = arrayOf(home, away)
                    val winningPlayersRaw = teams[winningTeam].players
                    val winningPlayers = winningPlayersRaw.map {
                        it.name
                    }.toTypedArray()
                    val winningPlayersBooleanArray = booleanArrayOf(false, false, false, false, false)
                    var scorer = 300
                    var assistor = 300

                    playerAlertDialog.setMultiChoiceItems(winningPlayers, winningPlayersBooleanArray) {dialog, which, isChecked ->
                        if (isChecked) {
                            scorer = which
                        }
                    }.setPositiveButton("Next") {d, _ ->
                        val assistAlertDialog = AlertDialog.Builder(this.context)
                        assistAlertDialog.setTitle("Who Assisted?")
                        val booleanArrayAssistors = booleanArrayOf(false, false, false, false, false)
                        assistAlertDialog.setMultiChoiceItems(winningPlayers, booleanArrayAssistors) {dialog, which, isChecked ->
                            if (isChecked) {
                                assistor = which
                            }
                        }.setPositiveButton("Done") {dia, _ ->
                            var homeScore = 0
                            var awayScore = 0
                            if (winningTeam == 0) {
                                homeScore = 1
                            } else if (winningTeam == 1) {
                                awayScore = 1
                            }
                            val current = LocalDateTime.now()
                            println(current)
                            val date = Date.from(current.atZone(ZoneId.systemDefault()).toInstant())
                            val goal = Goal(
                                winningPlayersRaw[scorer],
                                winningPlayersRaw[assistor],
                                goalCount,
                                if (winningTeam == 0) "home" else "away"
                            )
                            val goals = arrayListOf<Goal>(goal)
                            val uuid = UUID.randomUUID()
                            println(uuid)



                            val game = Game(home, away, homeScore, awayScore, date, goals)

                            //This print should carry it to firebase

                            database.child("${home.teamName} vs ${away.teamName} $uuid").setValue(game).addOnSuccessListener {
                                Snackbar.make(view.findViewById(R.id.stop_button), "Game successfully written to the database", Snackbar.LENGTH_SHORT).show()
                                val setsFragment = SetsFragment(null)
                                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.timer_fragment, setsFragment)?.commit()
                            }.addOnFailureListener {
                                Snackbar.make(view.findViewById(R.id.stop_button), "Something went wrong", Snackbar.LENGTH_SHORT).show()

                            }


                        }.show()

                    }.show()
                }
            }.show()

        }
    }

    @SuppressLint("SetTextI18n")
    private fun pauseCountDown(view: View, countdown: MinuteCountDown) {
        val pauseButton = view.findViewById<Button>(R.id.pause_button)
        val minuteTextView = view.findViewById<TextView>(R.id.minuteTextView)
        val secondTextView = view.findViewById<TextView>(R.id.secondTextView)

        pauseButton.setOnClickListener {
            if (!isPaused) {

                //Logic for ongoing pause timer goes here
                if (!pause)
                {
                    tempPauseSecondData = secondTextView.text.toString().toInt()
                    tempPauseMinuteData = minuteTextView.text.toString().toInt()


                    countdown.secondCountDown.cancel()
                    countdown.cancel()
                    pauseButton.text = "RESUME"
                } else
                {
                    tempPauseSecondData = secondTextView.text.toString().toInt()
                    tempPauseMinuteData = minuteTextView.text.toString().toInt()

                    minuteCountDown.cancel()
                    minuteCountDown.secondCountDown.cancel()
                    secondCountDown.cancel()
                    pauseButton.text = "RESUME"
                }
                //Closing tag for logic for ongoing pause timer


                if (tempPauseMinuteData < 10) {
                    minuteTextView.text = "0${tempPauseMinuteData.toString()}"
                } else {
                    minuteTextView.text = tempPauseMinuteData.toString()
                }

                if (tempPauseSecondData < 10) {
                    secondTextView.text = "0${tempPauseSecondData.toString()}"
                } else {
                    secondTextView.text = tempPauseSecondData.toString()
                }

                isPaused = true
                pauseButton.text = "PAUSE"
            } else {
                val newSecondsInterval: Int = 60 - tempPauseSecondData
                pause = true
                isPaused = false
                minuteCountDown = MinuteCountDown(((countdownduration * 60 * 1000) - ((tempPauseMinuteData + 1) * 60000)).toLong(), 60000, minuteTextView, (tempPauseMinuteData + 1), this.context, secondCountDown)
                secondCountDown = SecondCountDown((newSecondsInterval * 1000).toLong(), 1000, secondTextView, tempPauseSecondData, minuteCountDown, pause)
                secondCountDown.start()


            }
        }

    }
}