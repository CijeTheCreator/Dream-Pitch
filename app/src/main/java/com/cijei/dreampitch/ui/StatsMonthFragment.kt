package com.cijei.dreampitch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.cijei.dreampitch.R
import com.cijei.dreampitch.data.PlayerMonthData
import com.google.firebase.database.*

class StatsMonthFragment: Fragment() {

    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.stats_month_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = FirebaseDatabase.getInstance("https://dream-pitch-default-rtdb.firebaseio.com/").getReference("Stats")
        database.addListenerForSingleValueEvent(statsFetcherListener())
    }

    private fun statsFetcherListener(): ValueEventListener {
        val statsFetcherListener = object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val docs = p0.children
                val playerMonthDataArray = ArrayList<PlayerMonthData>()
                var count = 0
                for (player in docs) {
                    count ++
                    println(count)
                    val playerName = player.key
                    val playerGoals = player.child("goals").value as Long
                    val playerAssists = player.child("assists").value as Long
                    val playerWins = player.child("wins").value as Long
                    val playerMonthData = PlayerMonthData(playerName!!, playerGoals.toInt(), playerAssists.toInt(), playerWins.toInt())
                    playerMonthDataArray.add(playerMonthData)
                }

                val goalRankingArrayList = playerMonthDataArray.sortedBy {
                    it.goals
                }

                val assistRankingArrayList = playerMonthDataArray.sortedBy {
                    it.assists
                }

                val cleanShitRankingArrayList = playerMonthDataArray.sortedBy {
                    it.wins
                }

                val goalLeader1 = view?.findViewById<TextView>(R.id.goal_leader_1_text_View)
                val goalLeader1Image = view?.findViewById<ImageView>(R.id.goal_leader_jersery)
                val goalLeader1Goals = view?.findViewById<TextView>(R.id.goalLeader1Goals)

                val goalLeader2 = view?.findViewById<TextView>(R.id.goalLeader2)
                val goalLeader2Goals = view?.findViewById<TextView>(R.id.goalLeader2Goals)

                val goalLeader3 = view?.findViewById<TextView>(R.id.goalLeader3)
                val goalLeader3Goals = view?.findViewById<TextView>(R.id.goalLeader3Goals)

                val goalLeader4 = view?.findViewById<TextView>(R.id.goalLeader4)
                val goalLeader4Goals = view?.findViewById<TextView>(R.id.goalLeader4Goals)

                val assistLeader = view?.findViewById<TextView>(R.id.assistLeader)
                val assistLeaderAssists = view?.findViewById<TextView>(R.id.assistLeaderAssists)
                val assistsLeaderJersey = view?.findViewById<ImageView>(R.id.assistLeaderJersey)

                val cleanSheetsLeader = view?.findViewById<TextView>(R.id.cleanSheetsLeader)
                val cleanSheetsLeaderCleenSheets = view?.findViewById<TextView>(R.id.cleanSheetLeaderCleenSheets)
                val cleanSheetsLeaderJersey = view?.findViewById<ImageView>(R.id.cleanSheetsLeaderJersey)

                goalLeader1?.text = goalRankingArrayList[goalRankingArrayList.size - 1].playerName
                goalLeader1Goals?.text = goalRankingArrayList[goalRankingArrayList.size - 1].goals.toString()
//                goalLeader1Image?.setImageResource(imageResourceFetcher(goalRankingArrayList[goalRankingArrayList.size - 1].playerName))

                goalLeader2?.text = goalRankingArrayList[goalRankingArrayList.size - 2].playerName
                goalLeader2Goals?.text = goalRankingArrayList[goalRankingArrayList.size - 2].goals.toString()

                goalLeader3?.text = goalRankingArrayList[goalRankingArrayList.size - 3].playerName
                goalLeader3Goals?.text = goalRankingArrayList[goalRankingArrayList.size - 3].goals.toString()

                goalLeader4?.text = goalRankingArrayList[goalRankingArrayList.size - 4].playerName
                goalLeader4Goals?.text = goalRankingArrayList[goalRankingArrayList.size - 4].goals.toString()

                assistLeader?.text = assistRankingArrayList[assistRankingArrayList.size - 1].playerName
                assistLeaderAssists?.text = assistRankingArrayList[assistRankingArrayList.size - 1].assists.toString()
//                assistsLeaderJersey?.setImageResource(imageResourceFetcher(assistRankingArrayList[assistRankingArrayList.size - 1].playerName))

                cleanSheetsLeader?.text = cleanShitRankingArrayList[cleanShitRankingArrayList.size - 1].playerName
                cleanSheetsLeaderCleenSheets?.text = cleanShitRankingArrayList[cleanShitRankingArrayList.size - 1].wins.toString()
//                cleanSheetsLeaderJersey?.setImageResource(imageResourceFetcher(cleanShitRankingArrayList[cleanShitRankingArrayList.size - 1]))

            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        return statsFetcherListener
    }
}