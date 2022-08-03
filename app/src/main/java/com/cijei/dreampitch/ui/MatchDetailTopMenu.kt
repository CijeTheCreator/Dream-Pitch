package com.cijei.dreampitch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.cijei.dreampitch.R
import com.cijei.dreampitch.mock.MockMatches
import com.google.android.material.snackbar.Snackbar

class MatchDetailTopMenu: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.match_detail_top_menu_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val detailsButton = view.findViewById<TextView>(R.id.match_details_textView)
        val lineupsButton = view.findViewById<TextView>(R.id.match_lineups_textView)
        //TODO("This should be gotten onclick, get the game from the games database")
        val game = MockMatches().getMatches(null)[0]

        detailsButton.setOnClickListener {
            val matchDetailFragment = MatchDetails(game)
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.match_details_body, matchDetailFragment)?.commit()
        }

        lineupsButton.setOnClickListener {
            if (game.home != null) {
                val matchDetailHomeLineupFragment = MatchDetailHomeLineupFragment(game.home!!, game)
                val fm = activity?.supportFragmentManager
                val transaction = fm?.beginTransaction()?.replace(R.id.match_details_body, matchDetailHomeLineupFragment)?.commit()
            }
        }

    }
}