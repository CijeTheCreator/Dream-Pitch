package com.cijei.dreampitch.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.cijei.dreampitch.R
import com.cijei.dreampitch.adapters.PlayerSearchAdapter
import com.cijei.dreampitch.data.MatchDay
import com.cijei.dreampitch.data.Player
import com.cijei.dreampitch.data.Set
import com.cijei.dreampitch.data.Stat
import com.cijei.dreampitch.databinding.SelectPlayerFragmentBinding
import com.cijei.dreampitch.mock.MockPlayers
import com.cijei.dreampitch.mock.MockSets
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.*
import org.w3c.dom.Text
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class SelectPlayerActivity : AppCompatActivity() {

    private lateinit var binding: SelectPlayerFragmentBinding
    private lateinit var editText: EditText
    private lateinit var players: List<Player>
    private lateinit var adapter: PlayerSearchAdapter
    private var selectedPlayers: ArrayList<Player> = ArrayList<Player>()
    private var sets: ArrayList<Set> = ArrayList<Set>()
    private lateinit var database: DatabaseReference
    private lateinit var playerDatabase: DatabaseReference

    private lateinit var statDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = FirebaseDatabase.getInstance("https://dream-pitch-default-rtdb.firebaseio.com").getReference("Sets")
        statDatabase = FirebaseDatabase.getInstance("https://dream-pitch-default-rtdb.firebaseio.com").getReference("Stats")

        playerDatabase = FirebaseDatabase.getInstance("https://dream-pitch-default-rtdb.firebaseio.com").getReference("Players")

        binding = SelectPlayerFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val searchEditText = binding.playerSearchEditText
        searchEditText.addTextChangedListener(textWatcher)

        val extras = intent.extras
        val keys = extras?.get("Keys") as ArrayList<String>
        for (key in keys) {
            val set = extras.get(key) as Set
            sets.add(set)
        }

        playerDatabase.addValueEventListener(playerDataListener)
    }

    val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val searchPlayers = players.filter { player ->
                player.name.contains(p0!!, true)
            }
            adapter.setSearchData(searchPlayers)
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    }

    private fun createSet(selectedPlayers: ArrayList<Player>, view: View): Set {
        val newSet = Set()
//        val uuid = UUID.randomUUID()
        newSet.teamName = "Team ${selectedPlayers[0].name}"
        newSet.wins = 0
        newSet.loss = 0
        newSet.draws = 0
        newSet.players = selectedPlayers
        val date = LocalDateTime.now()


        database.child("${date.year} ${date.month} ${date.dayOfMonth}").child(newSet.teamName).setValue(newSet).addOnSuccessListener {
            Snackbar.make(view, "${newSet.teamName} created successfully", Snackbar.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Snackbar.make(view, "Creation failed", Snackbar.LENGTH_SHORT).show()

        }

        return newSet
    }

    private fun mainCode(playerz: ArrayList<Player>) {
        binding.playerSelectProgressBar.visibility = View.INVISIBLE
//        val mockPlayers = MockPlayers()
        players = playerz
        adapter = PlayerSearchAdapter(players, selectedPlayers)
        val layoutManager = LinearLayoutManager(this)
        binding.playerSearchRecyclerView.adapter = adapter
        binding.playerSearchRecyclerView.layoutManager = layoutManager

        val floatingActionButton: FloatingActionButton = binding.floatingActionButton
        floatingActionButton.setOnClickListener {

            val set = createSet(selectedPlayers, it)
            sets.add(set)
            val bundle = Bundle()
            val keyz = ArrayList<String>()
            for (set in sets) {
                keyz.add(set.teamName)
                bundle.putParcelable(set.teamName, set)
            }
            bundle.putStringArrayList("Keys", keyz)

            val i = Intent(this@SelectPlayerActivity, MainActivity::class.java)
            i.putExtras(bundle)
            startActivity(i)
        }

        val addButton: Button = binding.addButton
        addButton.setOnClickListener {
            val editText: EditText = EditText(this)
            val dialog = AlertDialog.Builder(this).setView(editText).setTitle("Add Player").setMessage("Enter Player Name")
            dialog.setPositiveButton("Add") { _, _ ->
                val playerName = editText.text.toString()
                val player_ = Player()
                player_.name = playerName
                player_.club = "LIV"
                player_.position = "MID"
//                mockPlayers.addPlayer(player_.name,player_.position, player_.club)
                playerDatabase.child(playerName).setValue(player_)
                statDatabase.child(playerName).setValue(Stat(0, 0, 0, 0, 0, ArrayList<MatchDay>()))
                val newPlayerList = players as ArrayList<Player>
                adapter.setSearchData(newPlayerList)

            }
            dialog.create().show()
        }
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
