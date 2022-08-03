package com.cijei.dreampitch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.cijei.dreampitch.R
import com.cijei.dreampitch.data.FantasyPlayer
import com.cijei.dreampitch.data.Player
import com.cijei.dreampitch.mock.MockFantasyScores

class FantasyPointsFragment(val playerz: ArrayList<Player>): Fragment() {


    private lateinit var players: ArrayList<Player>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fantasy_points_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments?.get("keyz") != null){
            players = ArrayList<Player>()
            val keys = arguments?.get("keyz") as ArrayList<String>
            for (key in keys) {
                val player = arguments?.get(key) as Player
                players.add(player)
            }
        } else {
            players = playerz
        }

        val fantasyPlayers = dynamicify(view)
        //TODO("For each player, get the player's score from the fantasyDatabase")
        val fantasyScores = MockFantasyScores(players).getFantasyScores()

        for (i in 0 until fantasyScores.size) {
            fantasyPlayers[i].setNameAndScore(fantasyScores[i])
        }

    }

    private fun dynamicify(view: View): ArrayList<FantasyPlayer> {
        //Top Values
        //TODO("Also get these from the general fantasyScores database")
        val fantasyPointsAverage = view.findViewById<TextView>(R.id.fantasy_points_average_text_view)
        val fantasyPointsYourScore = view.findViewById<TextView>(R.id.fantasy_points_your_score_textview)
        val fantasyPointsHighest = view.findViewById<TextView>(R.id.fantasy_points_highest_textview)

        //Player Names
        val fantasyStrikerName = view.findViewById<TextView>(R.id.fantasy_striker_name_text_view)
        val fantasyMidfielderName = view.findViewById<TextView>(R.id.fantasy_midfielder_name_textview)
        val fantasyDefender1Name = view.findViewById<TextView>(R.id.fantasy_defender_1_name_textview)
        val fantasyDefender2Name = view.findViewById<TextView>(R.id.fantasy_defender_2_name_text_view)
        val fantasySub1Name = view.findViewById<TextView>(R.id.fantasy_sub1_name_textview)
        val fantasySub2Name = view.findViewById<TextView>(R.id.fantasy_sub2_name_textview)
        val fantasySub3Name = view.findViewById<TextView>(R.id.fantasy_sub3_name_textview)
        val fantasyGoalieName = view.findViewById<TextView>(R.id.fantasy_goalie_name_textview)

        //Players
        val fantasySub2 = view.findViewById<LinearLayout>(R.id.fantasy_sub2)
        val fantasySub1 = view.findViewById<LinearLayout>(R.id.fantasy_sub1)
        val fantasySub3 = view.findViewById<LinearLayout>(R.id.fantasy_sub3)
        val fantasyStriker = view.findViewById<LinearLayout>(R.id.fantasy_striker)
        val fantasyMidfielder = view.findViewById<LinearLayout>(R.id.fantasy_midfielder)
        val fantasyDefender1 = view.findViewById<LinearLayout>(R.id.fantasy_defender_1)
        val fantasyDefender2 = view.findViewById<LinearLayout>(R.id.fantasy_defender_2)
        val fantasyGoalie = view.findViewById<LinearLayout>(R.id.fantasy_goalie)

        //Player Scores
        val fantasySub1Score = view.findViewById<TextView>(R.id.fantasy_sub1_score_textview)
        val fantasySub2Score = view.findViewById<TextView>(R.id.fantasy_sub2_score_textview)
        val fantasySub3Score = view.findViewById<TextView>(R.id.fantasy_sub3_score_textview)
        val fantasyStrikerScore = view.findViewById<TextView>(R.id.fantasy_striker_score_text_view)
        val fantasyMidfielderScore = view.findViewById<TextView>(R.id.fantasy_midfielder_points_textview)
        val fantasyDefender1Score = view.findViewById<TextView>(R.id.fantasy_defender_1_score_textview)
        val fantasyDefender2Score = view.findViewById<TextView>(R.id.fantasy_defender_2_score_textview)
        val fantasyGoalieScore = view.findViewById<TextView>(R.id.fantasy_goalie_score_textview)

        val f_Striker = FantasyPlayer(fantasyStriker, fantasyStrikerScore, fantasyStrikerName)
        val f_Midfielder = FantasyPlayer(fantasyMidfielder, fantasyMidfielderScore, fantasyMidfielderName)
        val f_Defender1 = FantasyPlayer(fantasyDefender1, fantasyDefender1Score, fantasyDefender1Name)
        val f_Defender2 = FantasyPlayer(fantasyDefender2, fantasyDefender2Score, fantasyDefender2Name)
        val f_Goalie = FantasyPlayer(fantasyGoalie, fantasyGoalieScore, fantasyGoalieName)
        val f_Sub1 = FantasyPlayer(fantasySub1, fantasySub1Score, fantasySub1Name)
        val f_Sub2 = FantasyPlayer(fantasySub2, fantasySub2Score, fantasySub2Name)
        val f_Sub3 = FantasyPlayer(fantasySub3, fantasySub3Score, fantasySub3Name)

        val fantasyPlayers = ArrayList<FantasyPlayer>()
        fantasyPlayers.add(f_Striker)
        fantasyPlayers.add(f_Midfielder)
        fantasyPlayers.add(f_Defender1)
        fantasyPlayers.add(f_Defender2)
        fantasyPlayers.add(f_Goalie)
        fantasyPlayers.add(f_Sub1)
        fantasyPlayers.add(f_Sub2)
        fantasyPlayers.add(f_Sub3)

        return fantasyPlayers
    }

}

