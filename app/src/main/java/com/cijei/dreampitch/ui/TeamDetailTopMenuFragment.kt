package com.cijei.dreampitch.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.cijei.dreampitch.R

class TeamDetailTopMenuFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.team_details_top_menu_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val matchesMenuButton = view.findViewById<TextView>(R.id.matchesTextView)
        val lineupsButton = view.findViewById<TextView>(R.id.lineupsTextView)

        matchesMenuButton.setOnClickListener() {
            val matchesFragment = TeamDetailMatchesFragment()
            val supportManager = activity?.supportFragmentManager
            supportManager?.beginTransaction()?.replace(R.id.recycler_views_fragment, matchesFragment)?.commit()
        }
    }


}