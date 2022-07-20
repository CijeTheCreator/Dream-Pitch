package com.cijei.dreampitch.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.databinding.StatsPlayerItemBinding

class StatsPlayersViewHolder(databinding: StatsPlayerItemBinding): RecyclerView.ViewHolder(databinding.root) {
    val playerNameTextview = databinding.statsPlayerName
    val playerPostionTextview = databinding.statsPlayerPosition
    val playerItem = databinding.statsPlayer
}