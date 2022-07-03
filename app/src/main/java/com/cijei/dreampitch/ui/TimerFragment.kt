package com.cijei.dreampitch.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.cijei.dreampitch.R
import com.cijei.dreampitch.hood.MinuteCountDown
import com.cijei.dreampitch.hood.SecondCountDown

class TimerFragment: Fragment() {

    private var tempPauseMinuteData: Int = 0
    private var tempPauseSecondData: Int = 0
    private var isPaused = false
    private lateinit var minuteCountDown: MinuteCountDown
    private lateinit var secondCountDown: SecondCountDown
    private var pause: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_timer_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        startCountDown(view, 2)
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

    private fun stopCountDown(view: View, countdown: MinuteCountDown, currentMinutes: String = "00", currentSeconds: String = "00") {
        val stopButton = view.findViewById<Button>(R.id.stop_button)
        val minuteTextView = view.findViewById<TextView>(R.id.minuteTextView)
        val secondTextView = view.findViewById<TextView>(R.id.secondTextView)
        stopButton.setOnClickListener {
            countdown.cancel()
            countdown.secondCountDown.cancel()
            countdown.count = 0
            countdown.secondCountDown.count = 0
            minuteTextView.text = currentMinutes
            secondTextView.text = currentSeconds
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
                } else
                {
                    tempPauseSecondData = secondTextView.text.toString().toInt()
                    tempPauseMinuteData = minuteTextView.text.toString().toInt()

                    secondCountDown.cancel()
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
            } else {
                val newSecondsInterval: Int = 60 - tempPauseSecondData
                secondCountDown = SecondCountDown((newSecondsInterval * 1000).toLong(), 1000, secondTextView, tempPauseSecondData)
                secondCountDown.start()
                isPaused = false
                pause = true

            }
        }

    }
}