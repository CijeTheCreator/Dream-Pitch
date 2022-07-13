package com.cijei.dreampitch.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.data.Game
import com.cijei.dreampitch.databinding.MatchesByDateItemBinding
import com.cijei.dreampitch.viewholders.MatchesByDateViewHolder

class MatchesByDateRecyclerViewAdapter(val matchesByDate: ArrayList<ArrayList<Game>>, val context: Context): RecyclerView.Adapter<MatchesByDateViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesByDateViewHolder {
        return MatchesByDateViewHolder(MatchesByDateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MatchesByDateViewHolder, position: Int) {
        val layoutManager = LinearLayoutManager(context)
        holder.matchesByDateRecyclerView.layoutManager = layoutManager
        val adapter = MatchesByDateRecyclerViewItemAdapter(matchesByDate[position])
        holder.matchesByDateRecyclerView.adapter = adapter

        holder.matchDate.text = matchesByDate[position][0].date.toString()
    }

    override fun getItemCount(): Int {
       return matchesByDate.size
    }
}