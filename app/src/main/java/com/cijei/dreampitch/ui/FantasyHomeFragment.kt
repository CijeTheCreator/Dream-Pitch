package com.cijei.dreampitch.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.cijei.dreampitch.R
import com.cijei.dreampitch.mock.MockFantasyData
import com.cijei.dreampitch.mock.MockPlayers
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.random.Random

class FantasyHomeFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fantasy_home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentPlayer = MockPlayers().getPlayers()[0]

        val average = view.findViewById<TextView>(R.id.fantasy_average)
        val yourScore = view.findViewById<TextView>(R.id.fantasy_your_score)
        val highest = view.findViewById<TextView>(R.id.fantasy_highest)
        val date = view.findViewById<TextView>(R.id.fantasy_date)

        val miniTableName1 = view.findViewById<TextView>(R.id.fantasy_league_player_1)
        val miniTableScore1 = view.findViewById<TextView>(R.id.fanstasy_league_score_1)
        val miniTableName2 = view.findViewById<TextView>(R.id.fantasy_league_player_2)
        val miniTableScore2 = view.findViewById<TextView>(R.id.fantasy_league_score_2)
        val miniTableName3 = view.findViewById<TextView>(R.id.fantasy_league_player_3)
        val miniTableScore3 = view.findViewById<TextView>(R.id.fantasy_league_score_3)
        val miniTableName4 = view.findViewById<TextView>(R.id.fantasy_league_player_4)
        val miniTableScore4 = view.findViewById<TextView>(R.id.fantasy_league_score_4)

        val pointsButton = view.findViewById<View>(R.id.points_button)
        val squadButton = view.findViewById<View>(R.id.squad_button)
        val pickTeamButton = view.findViewById<View>(R.id.pick_team_button)

        val names = arrayListOf<TextView>(miniTableName1, miniTableName2, miniTableName3, miniTableName4)
        val scores = arrayListOf<TextView>(miniTableScore1, miniTableScore2, miniTableScore3, miniTableScore4)

        val fantasyData = MockFantasyData().getFantasyData()

        for (i in  0 until fantasyData.size){
            val score = fantasyData[i].weekData[0].score
            val date = fantasyData[i].weekData[0].date

            if (i < names.size) {
                names[i].text = fantasyData[i].player.name
                scores[i].text = fantasyData[i].totalScore.toString()
            }
        }

        val currentDate = Date(2022, 7, 23)
        date.text = "${currentDate.day}, ${currentDate.date}, ${currentDate.month}, ${currentDate.year}"

        val currentPlayerFantasyData = fantasyData[0]

        average.text = Random.nextInt(10, 50).toString()
        yourScore.text = currentPlayerFantasyData.weekData[0].score.toString()
        highest.text = Random.nextInt(50, 100).toString()

        pointsButton.setOnClickListener {

            val players = MockPlayers().getPlayers()

            val fantasyPointsFragment = FantasyPointsFragment(players)
            val fm = activity?.supportFragmentManager
            fm?.beginTransaction()?.replace(R.id.timer_fragment, fantasyPointsFragment)?.commit()
        }

        squadButton.setOnClickListener {
            Snackbar.make(it, "To Squad", Snackbar.LENGTH_SHORT).show()
        }

        pickTeamButton.setOnClickListener {
            Snackbar.make(it, "Pick Team", Snackbar.LENGTH_SHORT).show()
            val i = Intent(this.requireContext(), FantasySelectPlayers::class.java)
            startActivity(i)
        }




    }
}