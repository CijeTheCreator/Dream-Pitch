package com.cijei.dreampitch.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.R
import com.cijei.dreampitch.adapters.SetsAdapter
import com.cijei.dreampitch.data.Game
import com.cijei.dreampitch.data.Player
import com.cijei.dreampitch.data.Set
import com.cijei.dreampitch.mock.MockMatches
import com.cijei.dreampitch.mock.MockSets
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.*
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class SetsFragment(private var setz: ArrayList<Set>?): Fragment() {

    private lateinit var adapter: SetsAdapter
    private lateinit var sets: ArrayList<Set>
    private lateinit var database: DatabaseReference
    private lateinit var setsFromDatabase: ArrayList<Set>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sets_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        checkIfFragmentAttached {
            val date = LocalDateTime.now()
            database = FirebaseDatabase.getInstance("https://dream-pitch-default-rtdb.firebaseio.com/").getReference("Sets").child("${date.year} ${date.month} ${date.dayOfMonth}")


            setsFromDatabase = ArrayList<Set>()
            //TODO("Get these sets from the set database")


            val listener = object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    for (postSnapshot in p0.children) {
                        val set = Set()
                        val draws = postSnapshot.child("draws").value as Long
                        val loss = postSnapshot.child("loss").value as Long
                        val wins = postSnapshot.child("wins").value as Long
                        val teamName = postSnapshot.child("teamName").value as String

                        val players = postSnapshot.child("players").children
                        val playerxs = ArrayList<Player>()
                        for (player in players) {
                            val playerx = Player()
                            val playerName = player.child("name").value as String
                            val playerClub = player.child("club").value as String
                            val playerPosition = player.child("position").value as String

                            playerx.name = playerName
                            playerx.club = playerClub
                            playerx.position = playerPosition

                            playerxs.add(playerx)
                        }

                        set.teamName = teamName
                        set.wins = wins.toInt()
                        set.loss = loss.toInt()
                        set.draws = draws.toInt()
                        set.players = playerxs

                        setsFromDatabase.add(set)
                    }

                    println(setsFromDatabase.map {
                        it.players.map {
                            it.name
                        }
                    })
                    view.findViewById<ProgressBar>(R.id.setLoaderProgressBar).visibility = View.INVISIBLE
                    mainCode(view, setsFromDatabase, this@checkIfFragmentAttached)
                }


                override fun onCancelled(p0: DatabaseError) {
                    Snackbar.make(view.findViewById(R.id.remove_sets_button), p0.message, Snackbar.LENGTH_SHORT).show()
                }


            }


            database.addValueEventListener(listener)
        }
    }


    private fun mainCode(view: View, setiz: ArrayList<Set>, context: Context) {
//        val setsHandler = MockSets()
        sets = setiz

        //TODO("Remember to undo this")
//        if (setz != null) {
//            sets = setz as ArrayList<Set>
//        }




        adapter = SetsAdapter(sets, context)
        val layoutManager = LinearLayoutManager(this.context)
        val recyclerView = view.findViewById<RecyclerView>(R.id.setsRecyclerView)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)


        if (arguments != null) {
            val setz = ArrayList<Set>()
            val keys = arguments?.get("Keys") as ArrayList<String>
            for (key in keys) {
                val set = arguments?.get(key) as Set
                setz.add(set)
            }
            //TODO("Add this set to the set database")
            adapter.addSet(setz)
            sets = setz
            Snackbar.make(view.findViewById(R.id.add_sets_button), "${setz[setz.size - 1].teamName} Added", Snackbar.LENGTH_SHORT).show()

        }

        //Add Button Functionality
        val addButton = view.findViewById<Button>(R.id.add_sets_button)
        addButton.setOnClickListener {
//            Snackbar.make(addButton, "Add Set", Snackbar.LENGTH_SHORT).show()
            val bundle = Bundle()

            val keys = ArrayList<String>()
            for (set in sets) {
                keys.add(set.teamName)
            }
            bundle.putStringArrayList("Keys", keys)


            for (set in sets) {
                bundle.putParcelable(set.teamName, set)
            }
            val i = Intent(this@SetsFragment.context, SelectPlayerActivity::class.java)
            i.putExtras(bundle)
            startActivity(i)

        }

        //Remove Button Functionality
        val removeButton = view.findViewById<Button>(R.id.remove_sets_button)
        removeButton.setOnClickListener {
            Snackbar.make(removeButton, "Remove Set", Snackbar.LENGTH_SHORT).show()
            val removeSetsFragment = RemoveSetsFragment(sets)
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.timer_fragment, removeSetsFragment)
            transaction?.commit()
        }

        val startMatchButton = view.findViewById<Button>(R.id.start_match_button)
        startMatchButton.setOnClickListener {
            val builder = AlertDialog.Builder(this.requireContext())
            val selectedSets = ArrayList<Int>()
            val setsArrayList = sets.map {
                it.teamName
            }
            val setsArray = setsArrayList.toTypedArray()
            val booleanArrayList = ArrayList<Boolean>()
            for (set in setsArray) {
                booleanArrayList.add(false)
            }
            val booleanArray = booleanArrayList.toBooleanArray()
            builder.setTitle("Select Teams")

            builder.setMultiChoiceItems(setsArray, booleanArray) { dialog, which, isChecked ->
                if (isChecked) {
                    selectedSets.add(which)
                } else if (selectedSets.contains(which)) {
                    selectedSets.remove(which)
                }
            }.setPositiveButton("Done") {dialog, id ->
                println(selectedSets)
                Snackbar.make(removeButton, "Done", Snackbar.LENGTH_SHORT).show()
                //Start the timer
                val timerFragment = TimerFragment(sets[selectedSets[0]], sets[selectedSets[1]])
                val fm = activity?.supportFragmentManager
                fm?.beginTransaction()?.replace(R.id.timer_fragment, timerFragment)?.commit()
            }
            builder.show()
        }
    }

    fun checkIfFragmentAttached(operation: Context.() -> Unit) {
        if (isAdded && context != null) {
            operation(requireContext())
        }
    }
}