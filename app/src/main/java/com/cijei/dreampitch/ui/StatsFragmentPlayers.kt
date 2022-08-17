package com.cijei.dreampitch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.R
import com.cijei.dreampitch.adapters.StatPlayersAdapter
import com.cijei.dreampitch.data.Player
import com.cijei.dreampitch.mock.MockPlayers
import com.google.firebase.database.*

class StatsFragmentPlayers: Fragment() {

    private lateinit var database: DatabaseReference
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.stats_players_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById<RecyclerView>(R.id.stats_fragment_players_recycler_view)

        database = FirebaseDatabase.getInstance("https://dream-pitch-default-rtdb.firebaseio.com/").getReference("Players")
        database.addValueEventListener(playerDataListener)



    }

    private fun mainCode(players: ArrayList<Player>)
    {
//        val players = MockPlayers().getPlayers()
        val layoutManager = LinearLayoutManager(this.context)
        val adapter = StatPlayersAdapter(players, this.requireContext())


        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    val playerDataListener = object : ValueEventListener {
        override fun onDataChange(p0: DataSnapshot) {
            val databasePlayers = p0.children
            val players = ArrayList<Player>()

            for (player in databasePlayers) {
                val name = player.child("name").value as String
                val position = player.child("position").value as String
                val club = player.child("club").value as String

                val newPlayer = Player()
                newPlayer.name = name
                newPlayer.position = position
                newPlayer.club = club

                players.add(newPlayer)
            }

            mainCode(players)

        }

        override fun onCancelled(p0: DatabaseError) {
            TODO("Not yet implemented")
        }

    }

}