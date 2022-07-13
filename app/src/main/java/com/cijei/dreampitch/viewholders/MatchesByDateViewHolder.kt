package com.cijei.dreampitch.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.databinding.MatchesByDateItemBinding

class MatchesByDateViewHolder(databinding: MatchesByDateItemBinding): RecyclerView.ViewHolder(databinding.root) {
    val matchesByDateRecyclerView = databinding.matchByDateItemRecyclerView
    val matchDate = databinding.textView10
}