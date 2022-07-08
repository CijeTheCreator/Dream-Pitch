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
    var selectedPlayers: ArrayList<Player>
): RecyclerView.Adapter<PlayerSearchViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerSearchViewHolder {
        return PlayerSearchViewHolder(PlayerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PlayerSearchViewHolder, position: Int) {
        val playerList = playerList
        val player = playerList[position]

        holder.playerNameTextView.text = player.name
        holder.winsTextView.text = player.position
        holder.lossTextView.text = player.club
        if (selectedPlayers.contains(player)) {
            holder.tick.visibility = View.VISIBLE
        } else {
            holder.tick.visibility = View.INVISIBLE
        }

        
        holder.player.setOnClickListener {
          if (selectedPlayers.contains(player)){
              selectedPlayers.remove(player)
              holder.tick.visibility = View.INVISIBLE
              println(selectedPlayers)
          }else {
              selectedPlayers.add(player)
              holder.tick.visibility = View.VISIBLE
              println(selectedPlayers)
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