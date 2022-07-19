package com.cijei.dreampitch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.R
import com.cijei.dreampitch.adapters.CareerStatsAdapter
import com.cijei.dreampitch.data.Player
import com.cijei.dreampitch.data.Stat
import com.cijei.dreampitch.mock.MockPlayers
import com.cijei.dreampitch.mock.MockStats
import kotlin.random.Random

class PlayerDetailsActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_details)

        //TODO("The previous activity should pass in the player data")
        val player = MockPlayers().getPlayers()[Random.nextInt(0, 4)]
        val stat = MockStats().getMockStats(player)

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