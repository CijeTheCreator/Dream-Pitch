package com.cijei.dreampitch.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.databinding.FantasyPlayerItemBinding

class FantasyPlayerListViewHolder(databinding: FantasyPlayerItemBinding): RecyclerView.ViewHolder(databinding.root) {
    val playerName = databinding.fantasyPlayerNameTextview
    val playerPosition = databinding.fantasyPlayerPositionTextView
    val form = databinding.fantasyFormTextView
    val price = databinding.fantasyPriceTextView
    val selected = databinding.fantasySelectedTextView
}