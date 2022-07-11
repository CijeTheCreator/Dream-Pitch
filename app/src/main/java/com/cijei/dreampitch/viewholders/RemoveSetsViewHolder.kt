package com.cijei.dreampitch.viewholders

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.databinding.RemoveSetFragmentBinding
import com.cijei.dreampitch.databinding.RemoveSetItemBinding

class RemoveSetsViewHolder(databinding: RemoveSetItemBinding): RecyclerView.ViewHolder(databinding.root) {
    val setNameTextView: TextView = databinding.setName
    val tick: ImageView = databinding.imageView2
    val removeSetItem = databinding.removeSetItem
}