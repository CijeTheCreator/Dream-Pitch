package com.cijei.dreampitch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.R
import com.cijei.dreampitch.adapters.MatchesByDateRecyclerViewAdapter
import com.cijei.dreampitch.mock.MockMatches


class TeamDetailMatchesFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.matches_by_date_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gamesByDate = MockMatches().getMatchesByDate()
        val matchesByDateRecyclerView = view.findViewById<RecyclerView>(R.id.matches_by_date_recycler_view)
//        val matchesByDateItemRecyclerView = view.findViewById<RecyclerView>(R.id.match_by_date_item_recycler_view)
        val adapter = this.context?.let {
            MatchesByDateRecyclerViewAdapter(gamesByDate, it)
        }
        matchesByDateRecyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(this.context)
        matchesByDateRecyclerView.layoutManager = layoutManager

    }
}