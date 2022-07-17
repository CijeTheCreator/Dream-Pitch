package com.cijei.dreampitch.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.data.Goal
import com.cijei.dreampitch.databinding.MatchDetailItemHomeBinding

class MatchDetailHomeViewHolder(var databinding: MatchDetailItemHomeBinding): RecyclerView.ViewHolder(databinding.root) {
    fun bind(goal: Goal) {
        databinding.timeTextView.text = "${goal.time}'"
        databinding.goalScorerTextView.text = goal.scorer?.name
        databinding.assistPlayerTextView.text = goal.assist?.name
        databinding.newScoreTextView.text = "1-0" //TODO("Work on this Later")
    }
}