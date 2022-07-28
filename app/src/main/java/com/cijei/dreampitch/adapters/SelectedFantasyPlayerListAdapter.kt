package com.cijei.dreampitch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.data.Player
import com.cijei.dreampitch.databinding.SelectedPlayerItemBinding
import com.cijei.dreampitch.viewholders.SelectedFantasyPlayerListViewHolder

class SelectedFantasyPlayerListAdapter(val playerz: ArrayList<Player>): RecyclerView.Adapter<SelectedFantasyPlayerListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectedFantasyPlayerListViewHolder {
        return SelectedFantasyPlayerListViewHolder(SelectedPlayerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SelectedFantasyPlayerListViewHolder, position: Int) {
        val player = playerz[position]
        holder.selectedPlayerNameTextView.text = player.name
        holder.selectedPlayer.setOnClickListener {
            removePlayer(player)
        }
    }

    override fun getItemCount(): Int {
        return playerz.size
    }

    fun addPlayer(player: Player) {
        playerz.add(player)
        this.notifyDataSetChanged()
    }

    fun removePlayer(player: Player) {
        playerz.remove(player)
        this.notifyDataSetChanged()
    }
}