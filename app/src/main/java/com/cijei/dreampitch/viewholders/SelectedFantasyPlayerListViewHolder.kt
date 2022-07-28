package com.cijei.dreampitch.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.databinding.SelectedPlayerItemBinding

class SelectedFantasyPlayerListViewHolder(databinding: SelectedPlayerItemBinding): RecyclerView.ViewHolder(databinding.root) {
    val selectedPlayerNameTextView = databinding.fantasySelectedPlayerNameTextview
    val selectedPlayer = databinding.selectedPlayer
}