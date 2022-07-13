package com.cijei.dreampitch.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.databinding.MatchItemBinding

class MatchesByDateItemViewHolder(databinding: MatchItemBinding): RecyclerView.ViewHolder(databinding.root) {
    val team1 = databinding.team1
    val team2 = databinding.team2
    val team1Score = databinding.team1Score
    val team2Score = databinding.team2Score
    val match = databinding.match
}