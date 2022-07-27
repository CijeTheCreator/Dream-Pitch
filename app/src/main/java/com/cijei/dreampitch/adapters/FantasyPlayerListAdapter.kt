package com.cijei.dreampitch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.data.Player
import com.cijei.dreampitch.databinding.FantasyPlayerItemBinding
import com.cijei.dreampitch.viewholders.FantasyPlayerListViewHolder
import kotlin.random.Random

class FantasyPlayerListAdapter(val players: ArrayList<Player>): RecyclerView.Adapter<FantasyPlayerListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FantasyPlayerListViewHolder {
        return FantasyPlayerListViewHolder(FantasyPlayerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FantasyPlayerListViewHolder, position: Int) {
        val player = players[position]
        holder.playerName.text = player.name
        holder.playerPosition.text = player.position
        holder.form.text = "${Random.nextInt(0, 10)}.0"
        holder.price.text = "$15.0"
        holder.selected.text = "23%"
    }

    override fun getItemCount(): Int {
        return players.size
    }
}