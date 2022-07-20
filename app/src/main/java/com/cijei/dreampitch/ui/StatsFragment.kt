package com.cijei.dreampitch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cijei.dreampitch.R

class StatsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.stats_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val statsFragmentHeader = StatsFragmentHeader()
        val statsFragmentPlayers = StatsFragmentPlayers()
        val fm = activity?.supportFragmentManager
        fm?.beginTransaction()?.replace(R.id.stats_header, statsFragmentHeader)?.commit()
        fm?.beginTransaction()?.replace(R.id.stats_body, statsFragmentPlayers)?.commit()
    }
}