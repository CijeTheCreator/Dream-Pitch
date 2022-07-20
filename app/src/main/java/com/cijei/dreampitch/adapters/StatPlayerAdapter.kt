package com.cijei.dreampitch.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.data.Player
import com.cijei.dreampitch.databinding.StatsPlayerItemBinding
import com.cijei.dreampitch.ui.PlayerDetailsActivity
import com.cijei.dreampitch.viewholders.StatsPlayersViewHolder
import com.google.android.material.snackbar.Snackbar

class StatPlayersAdapter(val playerz: ArrayList<Player>, val context: Context): RecyclerView.Adapter<StatsPlayersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsPlayersViewHolder {
        return StatsPlayersViewHolder(StatsPlayerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: StatsPlayersViewHolder, position: Int) {
        val player = playerz[position]
        holder.playerNameTextview.text = player.name
        holder.playerPostionTextview.text = player.position

        holder.playerItem.setOnClickListener {
            Snackbar.make(it, "To Player Stats", Snackbar.LENGTH_SHORT).show()

            val i = Intent(context, PlayerDetailsActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable("genetics", player)
            i.putExtras(bundle)
            context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return playerz.size
    }
}