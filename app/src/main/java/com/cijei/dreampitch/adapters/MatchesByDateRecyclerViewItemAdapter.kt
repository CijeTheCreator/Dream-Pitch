package com.cijei.dreampitch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.data.Game
import com.cijei.dreampitch.databinding.MatchItemBinding
import com.cijei.dreampitch.viewholders.MatchesByDateItemViewHolder
import com.google.android.material.snackbar.Snackbar

class MatchesByDateRecyclerViewItemAdapter(val matches: ArrayList<Game>): RecyclerView.Adapter<MatchesByDateItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesByDateItemViewHolder {
        return MatchesByDateItemViewHolder(MatchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MatchesByDateItemViewHolder, position: Int) {
        val match = matches[position]
        holder.team1.text = match.home?.teamName
        holder.team2.text = match.away?.teamName
        holder.team1Score.text = match.homeScore.toString()
        holder.team2Score.text = match.awayScore.toString()

        holder.match.setOnClickListener {
            Snackbar.make(it, "To Match Details", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return matches.size
    }

}