package com.cijei.dreampitch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.data.Player
import com.cijei.dreampitch.databinding.FantasyPlayerItemBinding
import com.cijei.dreampitch.viewholders.FantasyPlayerListViewHolder
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class FantasyPlayerListAdapter(var players: ArrayList<Player>, val selectedFantasyPlayerListAdapter: SelectedFantasyPlayerListAdapter): RecyclerView.Adapter<FantasyPlayerListViewHolder>() {
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
        holder.transparencyView.setOnClickListener {
            selectedFantasyPlayerListAdapter.addPlayer(player)
            println(selectedFantasyPlayerListAdapter.playerz.map {
                it.name
            })
            it.background.alpha = 250
        }
    }

    override fun getItemCount(): Int {
        return players.size
    }

    fun setSearchData(playerz: ArrayList<Player>) {
        players = playerz
        this.notifyDataSetChanged()
    }
}