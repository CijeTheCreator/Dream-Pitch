package com.cijei.dreampitch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.R
import com.cijei.dreampitch.adapters.FantasyPlayerListAdapter
import com.cijei.dreampitch.adapters.SelectedFantasyPlayerListAdapter
import com.cijei.dreampitch.mock.MockPlayers
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class FantasySelectPlayers : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fantasy_select_players)

        //Replace later
        val players = MockPlayers().getPlayers()

        //Player List RecyclerView
        val playerListRecyclerView = findViewById<RecyclerView>(R.id.player_list_recycler_view)
        val fantasyPlayerListAdapter = FantasyPlayerListAdapter(players)
        val layoutManager = LinearLayoutManager(this)
        playerListRecyclerView.adapter = fantasyPlayerListAdapter
        playerListRecyclerView.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(playerListRecyclerView.context, layoutManager.orientation)
        playerListRecyclerView.addItemDecoration(dividerItemDecoration)
        //Player List RecyclerView

        //Selected Players RecyclerView
        val selectedPlayersRecyclerView = findViewById<RecyclerView>(R.id.selected_players_recycler_view)
        val selectedPlayersLayoutManager = LinearLayoutManager(this)
        selectedPlayersLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        val selectedPlayersAdapter = SelectedFantasyPlayerListAdapter(players)
        selectedPlayersRecyclerView.adapter = selectedPlayersAdapter
        selectedPlayersRecyclerView.layoutManager = selectedPlayersLayoutManager
        val selectedPlayersDividerItemDecoration = DividerItemDecoration(selectedPlayersRecyclerView.context, selectedPlayersLayoutManager.orientation)
        selectedPlayersRecyclerView.addItemDecoration(selectedPlayersDividerItemDecoration)
        //Selected Players RecyclerView


        val floatingActionButton = findViewById<FloatingActionButton>(R.id.floatingActionButton2)
        floatingActionButton.setOnClickListener {
            Snackbar.make(it, "To Fantasy Squad Fragment", Snackbar.LENGTH_LONG).show()
        }
    }

}