package com.cijei.dreampitch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.R
import com.cijei.dreampitch.adapters.CareerStatsAdapter
import com.cijei.dreampitch.data.MatchDay
import com.cijei.dreampitch.data.Player
import com.cijei.dreampitch.data.Set
import com.cijei.dreampitch.data.Stat
import com.cijei.dreampitch.mock.MockPlayers
import com.cijei.dreampitch.mock.MockStats
import com.google.firebase.database.*
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class PlayerDetailsActivity() : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var player: Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_details)

        val data = intent.extras
        player = data?.get("genetics") as Player

        database = FirebaseDatabase.getInstance("https://dream-pitch-default-rtdb.firebaseio.com/").getReference("Stats")
        database.child(player.name).addValueEventListener(statListener)

    }

    val statListener = object: ValueEventListener {
        override fun onDataChange(p0: DataSnapshot) {
            val date = LocalDateTime.now()
            val appearances = p0.child("appearances").value as Long
            val goals = p0.child("goals").value as Long
            val wins = p0.child("wins").value as Long
            val losses = p0.child("losses").value as Long
            val assists = p0.child("assists").value as Long

            //TODO("Matchday")
            val matchDays = ArrayList<MatchDay>()
            for (matchday in p0.child("MatchDays").children) {
                println(matchday.value)
                val players_ = matchday.child("set").child("players").children
                val players = ArrayList<Player>()
                for (player_ in players_) {
                    val player = Player()
                    player.name = player_.child("name").value as String
                    player.club = player_.child("club").value as String
                    player.position = player_.child("position").value as String
                    players.add(player)
                }

                val setWins = matchday.child("set").child("wins").value as Long
                val setLoss = matchday.child("set").child("loss").value as Long
                val setDraws = matchday.child("set").child("draws").value as Long

                val iGoals = matchday.child("goals").value as Long
                val iAssists = matchday.child("assists").value as Long

                val set = Set()
                set.players = players
                set.teamName = matchday.child("set").child("teamName").value as String
                set.loss = setLoss.toInt()
                set.wins = setWins.toInt()
                set.draws = setWins.toInt()

                val day = matchday.child("date").child("date").value as Long
                val month = matchday.child("date").child("month").value as Long
                val year = matchday.child("date").child("year").value as Long

                val date = Date(day.toInt(), month.toInt(), year.toInt())

                val matchDay = MatchDay(set, date, iGoals.toInt(), iAssists.toInt())
                matchDays.add(matchDay)
            }

            val stat = Stat(appearances.toInt(), goals.toInt(), wins.toInt(), losses.toInt(), assists.toInt(),matchDays)
            mainCode(player, stat)
        }

        override fun onCancelled(p0: DatabaseError) {
            TODO("Not yet implemented")
        }

    }

    private fun mainCode(player: Player, stat: Stat) {
        val appearancesTextView = findViewById<TextView>(R.id.appearances_text_view)
        val goalsTextView = findViewById<TextView>(R.id.goals_text_view)
        val wins = findViewById<TextView>(R.id.wins_text_view)
        val losses = findViewById<TextView>(R.id.loss_text_view)

        val playerNameTextView = findViewById<TextView>(R.id.player_name_text_view)
        val playerPositionTextView = findViewById<TextView>(R.id.player_position_text_view)

        val careerRecyclerView = findViewById<RecyclerView>(R.id.career_recycler_view)

        appearancesTextView.text = stat.appearances.toString()
        goalsTextView.text = stat.goals.toString()
        wins.text = stat.wins.toString()
        losses.text = stat.losses.toString()

        playerNameTextView.text = player.name
        playerPositionTextView.text = player.position

        val adapter = CareerStatsAdapter(stat.matchDays)
        val layoutManager = LinearLayoutManager(this)
        careerRecyclerView.layoutManager = layoutManager
        careerRecyclerView.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(careerRecyclerView.context, layoutManager.orientation)
        careerRecyclerView.addItemDecoration(dividerItemDecoration)

    }
}