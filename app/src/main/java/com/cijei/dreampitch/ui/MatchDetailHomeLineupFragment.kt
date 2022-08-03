package com.cijei.dreampitch.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.cijei.dreampitch.R
import com.cijei.dreampitch.data.Game
import com.cijei.dreampitch.data.Player
import com.cijei.dreampitch.data.Set
import com.cijei.dreampitch.mock.MockSets
import com.google.android.material.snackbar.Snackbar

class MatchDetailHomeLineupFragment(val set: Set, val game: Game? = null): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.match_detail_lineup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val strikerImageView = view.findViewById<ImageView>(R.id.strikerImageView)
        val defenderImageView = view.findViewById<ImageView>(R.id.defenderImageView)
        val midfielder1ImageView = view.findViewById<ImageView>(R.id.midfielder1ImageView)
        val midfielder2ImageView = view.findViewById<ImageView>(R.id.midfielder2ImageView)
        val goalieImageView = view.findViewById<ImageView>(R.id.goalKeeper)

        val selectedPlayers = ArrayList<Player>()
        val selectedViewsGroup = ArrayList<ArrayList<View>>()


        val strikerTextView = view.findViewById<TextView>(R.id.strikerTextView)
        val defenderTextView = view.findViewById<TextView>(R.id.defenderTextView)
        val midfielder1TextView = view.findViewById<TextView>(R.id.midfielder1TextView)
        val midfielder2TextView = view.findViewById<TextView>(R.id.midfielder2TextView)
        val goalieTextView = view.findViewById<TextView>(R.id.goalKeeperTextView)

        val strikerViews = arrayListOf<View>(strikerImageView, strikerTextView)
        val midfielder1Views = arrayListOf<View>(midfielder1ImageView, midfielder1TextView)
        val midfielder2Views = arrayListOf<View>(midfielder2ImageView, midfielder2TextView)
        val defenderView = arrayListOf<View>(defenderImageView, defenderTextView)
        val goalieView = arrayListOf<View>(goalieImageView, goalieTextView)

        val viewGroups = arrayListOf<ArrayList<View>>(strikerViews, midfielder1Views, midfielder2Views, defenderView, goalieView)


        val leftChevron = view.findViewById<ImageView>(R.id.match_detail_lineup_left_chevron)
        val rightChevron = view.findViewById<ImageView>(R.id.match_detail_lineup_right_chevron)

        val teamNameTextView = view.findViewById<TextView>(R.id.team_name_text_view)


        teamNameTextView.text = set.teamName

        rightChevron.setOnClickListener {
                val awayLineupFragment = MatchDetailAwayLineupFragment(game?.away!!, game)
                val supportManager = activity?.supportFragmentManager
                supportManager?.beginTransaction()?.replace(R.id.match_details_body, awayLineupFragment)?.commit()
        }

        leftChevron.visibility = View.INVISIBLE

        leftChevron.setOnClickListener {
            Snackbar.make(goalieImageView, "Home Team Lineup", Snackbar.LENGTH_SHORT).show()

        }


        val attackers = set.players.filter {
            it.position.equals("ATT")
        }
        val midfielders = set.players.filter {
            it.position.equals("MID")
        }
        val defenders = set.players.filter {
            it.position.equals("DEF")
        }

        if (attackers.size == 1) {
            strikerTextView.text = attackers[0].name
            selectedPlayers.add(attackers[0])
            selectedViewsGroup.add(strikerViews)
            imageResourceSetter(strikerImageView, attackers[0])
        }

        if (midfielders.size == 2) {
            midfielder1TextView.text = midfielders[0].name
            midfielder2TextView.text = midfielders[1].name
            selectedPlayers.add(midfielders[0])
            selectedPlayers.add(midfielders[1])
            selectedViewsGroup.add(midfielder1Views)
            selectedViewsGroup.add(midfielder2Views)
            imageResourceSetter(midfielder1ImageView,midfielders[0])
            imageResourceSetter(midfielder2ImageView,midfielders[1])
        }

        if (defenders.size == 1) {
            defenderTextView.text = defenders[0].name
            selectedPlayers.add(defenders[0])
            selectedViewsGroup.add(defenderView)
            imageResourceSetter(defenderImageView, defenders[0])
        }

        val remainingPlayers = set.players.filter {
            !selectedPlayers.contains(it)
        }

        val remainingViews = viewGroups.filter {
            !selectedViewsGroup.contains(it)
        }

        if (remainingPlayers.size > 0)
        {
            for (i in 0 until remainingPlayers.size) {
                val player = remainingPlayers[i]
                val textView = remainingViews[i][1] as TextView
                val imageView = remainingViews[i][0] as ImageView

                textView.text = player.name
                imageResourceSetter(imageView, player)
            }
        }


    }

    fun imageResourceSetter(imageView: ImageView, player: Player){
        when (player.club) {
            "ARS" -> {
                imageView.setImageResource(R.drawable.ars)
            }

            "MCI" -> {
                imageView.setImageResource(R.drawable.mci)
            }

            "LIV" -> {
                imageView.setImageResource(R.drawable.liv)
            }

            "CHE" -> {
                imageView.setImageResource(R.drawable.che)
            }

            "MNU" -> {
                imageView.setImageResource(R.drawable.mnu)
            }

            else -> {
                imageView.setImageResource(R.drawable.ars)
            }
        }
        imageView.setOnClickListener {
            Snackbar.make(imageView, "To Player Details", Snackbar.LENGTH_SHORT).show()
            val i = Intent(this.requireContext(), PlayerDetailsActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable("genetics", player)
            i.putExtras(bundle)
            startActivity(i)
        }
    }
}