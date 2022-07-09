package com.cijei.dreampitch.viewholders

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.databinding.SetItemBinding
import com.cijei.dreampitch.databinding.SetsFragmentBinding

class SetsViewHolder(databinding: SetItemBinding): RecyclerView.ViewHolder(databinding.root) {
    val setNameTextView: TextView = databinding.setNameTextView
    val setWinsTextView: TextView = databinding.setWins
    val setDrawsTextView: TextView = databinding.setDraws
    val setLossTextView: TextView = databinding.setLosses
    val expandChevron: ImageView = databinding.rightChevron
}