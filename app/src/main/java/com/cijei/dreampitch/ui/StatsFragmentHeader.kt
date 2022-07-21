package com.cijei.dreampitch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.cijei.dreampitch.R
import com.google.android.material.snackbar.Snackbar

class StatsFragmentHeader: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.stats_header_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val playersButton = view.findViewById<TextView>(R.id.stats_players_menu_button)
        val monthButton = view.findViewById<TextView>(R.id.stats_month_menu_button)

        playersButton.setOnClickListener {
            Snackbar.make(view, "To Player Stats", Snackbar.LENGTH_SHORT).show()
            val fm = activity?.supportFragmentManager
            val statsFragmentPlayers = StatsFragmentPlayers()
            fm?.beginTransaction()?.replace(R.id.stats_body,statsFragmentPlayers)?.commit()
        }

        monthButton.setOnClickListener {
            val fm = activity?.supportFragmentManager
            val statsMonthFragment = StatsMonthFragment()
            fm?.beginTransaction()?.replace(R.id.stats_body, statsMonthFragment)?.commit()
        }
    }
}