package com.cijei.dreampitch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.R
import com.cijei.dreampitch.adapters.MatchesByDateRecyclerViewAdapter
import com.cijei.dreampitch.data.Database
import com.cijei.dreampitch.data.Game
import com.cijei.dreampitch.mock.MockMatches
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class TeamDetailMatchesFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.matches_by_date_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO("Get it from the Games Database, these games must contain the team")
        //TODO("This fragment should accept the clicked team")
        showProgressBar(view)
        GlobalScope.launch {
            val gamesbyDate = Database().getMatchesByDate()
            withContext(Dispatchers.Main) {
                hideProgressBar(view)
                println(gamesbyDate)
                updateUI(view, gamesbyDate)

            }
        }


    }

    private fun updateUI(view: View, gamesByDate: ArrayList<ArrayList<Game>>) {
        val matchesByDateRecyclerView = view.findViewById<RecyclerView>(R.id.matches_by_date_recycler_view)
        val adapter = this.context?.let {
            MatchesByDateRecyclerViewAdapter(gamesByDate, it)
        }
        matchesByDateRecyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(this.context)
        matchesByDateRecyclerView.layoutManager = layoutManager
    }

    private fun showProgressBar(view: View) {
        view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
    }

    private fun hideProgressBar(view: View) {
        view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.INVISIBLE
    }
}