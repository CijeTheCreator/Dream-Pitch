package com.cijei.dreampitch.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.adapters.PlayerSearchAdapter
import com.cijei.dreampitch.data.Player
import com.cijei.dreampitch.databinding.PlayerItemBinding
import org.w3c.dom.Text

class PlayerSearchViewHolder(databinding: PlayerItemBinding): RecyclerView.ViewHolder(databinding.root) {
    val player: ConstraintLayout = databinding.playerItem
    val playerNameTextView: TextView = databinding.playerNameTextView
    val winsTextView: TextView = databinding.winsTextView
    val drawsTextView: TextView = databinding.drawsTextView
    val lossTextView: TextView = databinding.lossTextView

    val tick: ImageView = databinding.imageView


}