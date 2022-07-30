package com.cijei.dreampitch.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.R
import com.cijei.dreampitch.adapters.CareerStatsAdapter
import com.cijei.dreampitch.adapters.FantasyPlayerListAdapter
import com.cijei.dreampitch.adapters.SelectedFantasyPlayerListAdapter
import com.cijei.dreampitch.data.Player
import com.cijei.dreampitch.mock.MockPlayers
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class FantasySelectPlayers : AppCompatActivity() {

    lateinit var selectedPlayersAdapter: SelectedFantasyPlayerListAdapter
    lateinit var fantasyPlayerListAdapter: FantasyPlayerListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fantasy_select_players)

        //Replace later
        val players = MockPlayers().getPlayers()


        //Selected Players RecyclerView
        val selectedPlayersRecyclerView = findViewById<RecyclerView>(R.id.selected_players_recycler_view)
        val selectedPlayersLayoutManager = LinearLayoutManager(this)
        selectedPlayersLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        selectedPlayersAdapter = SelectedFantasyPlayerListAdapter(ArrayList<Player>())
        selectedPlayersRecyclerView.adapter = selectedPlayersAdapter
        selectedPlayersRecyclerView.layoutManager = selectedPlayersLayoutManager
        val selectedPlayersDividerItemDecoration = DividerItemDecoration(selectedPlayersRecyclerView.context, selectedPlayersLayoutManager.orientation)
        selectedPlayersRecyclerView.addItemDecoration(selectedPlayersDividerItemDecoration)
        //Selected Players RecyclerView

        //Player List RecyclerView
        val playerListRecyclerView = findViewById<RecyclerView>(R.id.player_list_recycler_view)
        fantasyPlayerListAdapter = FantasyPlayerListAdapter(players, selectedPlayersAdapter)
        val layoutManager = LinearLayoutManager(this)
        playerListRecyclerView.adapter = fantasyPlayerListAdapter
        playerListRecyclerView.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(playerListRecyclerView.context, layoutManager.orientation)
        playerListRecyclerView.addItemDecoration(dividerItemDecoration)
        //Player List RecyclerView


        val floatingActionButton = findViewById<FloatingActionButton>(R.id.floatingActionButton2)
        floatingActionButton.setOnClickListener {

            //Get the names as an arraylist
            //TODO("Look for a more efficient way later")
            val playerArrayList = selectedPlayersAdapter.playerz
            val playerNameArrayList = ArrayList<String>()
            for (player in playerArrayList) {
                playerNameArrayList.add(player.name)
            }

            //Put the list into the bundle, this will serve as the list of keys
            val bundle = Bundle()
            bundle.putStringArrayList("keyz", playerNameArrayList)

            //Put the players, which coincidentally are already parcelized
            for (player in playerArrayList) {
                bundle.putParcelable(player.name, player)
            }

            val i = Intent(this, TempHostActivity::class.java)
            i.putExtras(bundle)

            startActivity(i)
        }

        val textWatcher = object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val searchPlayers = players.filter {
                    it.name.contains(p0!!, true)
                }
                val searchPlayerArrayList = ArrayList<Player>()
                for (player in searchPlayers) {
                    searchPlayerArrayList.add(player)
                }
                fantasyPlayerListAdapter.setSearchData(searchPlayerArrayList)
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        }

        val editText = findViewById<EditText>(R.id.search_fantasy_select_player)
        editText.addTextChangedListener(textWatcher)

    }


}