package com.cijei.dreampitch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.R
import com.cijei.dreampitch.adapters.MatchDetailAdapter
import com.cijei.dreampitch.data.Game
import org.w3c.dom.Text

class MatchDetails(var match: Game): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.match_detail_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val team1Text = view.findViewById<TextView>(R.id.team1TextView)
        val team2Text = view.findViewById<TextView>(R.id.team2TextView)
        val team1score = view.findViewById<TextView>(R.id.team1ScoreTextView)
        val team2score = view.findViewById<TextView>(R.id.team2ScoreTextView)

        val matchDetailsRecyclerView = view.findViewById<RecyclerView>(R.id.match_events_recycler_View)

        team1Text.text = match.home?.teamName
        team2Text.text = match.away?.teamName

        team1score.text = match.homeScore.toString()
        team2score.text = match.awayScore.toString()

        val layoutManager = LinearLayoutManager(this.context)
        val adapter = MatchDetailAdapter(match.goals)

        matchDetailsRecyclerView.adapter = adapter
        matchDetailsRecyclerView.layoutManager = layoutManager

    }
}