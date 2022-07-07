package com.cijei.dreampitch.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.R
import com.cijei.dreampitch.data.Player
import com.cijei.dreampitch.databinding.PlayerItemBinding
import com.cijei.dreampitch.viewholders.PlayerSearchViewHolder

class PlayerSearchAdapter(
    private var playerList: List<Player>,
    private var playerListener: PlayerListener
): RecyclerView.Adapter<PlayerSearchViewHolder>() {

    interface PlayerListener {
        fun onSelect(player: Player)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerSearchViewHolder {
        return PlayerSearchViewHolder(PlayerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PlayerSearchViewHolder, position: Int) {
        val playerList = playerList
        val player = playerList[position]

        holder.playerNameTextView.text = player.name
        holder.winsTextView.text = player.position
        holder.lossTextView.text = player.club

        holder.playerNameTextView.setOnClickListener {
            if (holder.tick.visibility ==   View.VISIBLE) {
                holder.tick.visibility = View.INVISIBLE
            } else {
                holder.tick.visibility = View.VISIBLE
            }
        }
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    fun setSearchData(playerListz: List<Player>) {
        playerList = playerListz
        this.notifyDataSetChanged()
    }
}