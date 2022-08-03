package com.cijei.dreampitch.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.adapters.MatchDetailAdapter.Const.AWAY
import com.cijei.dreampitch.adapters.MatchDetailAdapter.Const.HOME
import com.cijei.dreampitch.data.Game
import com.cijei.dreampitch.data.Goal
import com.cijei.dreampitch.databinding.MatchDetailItemAwayBinding
import com.cijei.dreampitch.databinding.MatchDetailItemHomeBinding
import com.cijei.dreampitch.viewholders.MatchDetailAwayViewHolder
import com.cijei.dreampitch.viewholders.MatchDetailHomeViewHolder

class MatchDetailAdapter(val goals: ArrayList<Goal>, val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private object Const{
        val HOME = 0
        val AWAY = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HOME) {
            MatchDetailHomeViewHolder(MatchDetailItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        } else {
            MatchDetailAwayViewHolder(MatchDetailItemAwayBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == HOME) {
            (holder as MatchDetailHomeViewHolder).bind(goals[position], context)

        } else {
            (holder as MatchDetailAwayViewHolder).bind(goals[position], context)
        }

    }

    override fun getItemCount(): Int {
        return goals.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (goals[position].team.equals("home")) HOME else AWAY
    }
}