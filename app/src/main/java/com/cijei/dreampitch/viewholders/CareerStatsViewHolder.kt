package com.cijei.dreampitch.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.databinding.CareerStatsItemBinding

class CareerStatsViewHolder(val databinding: CareerStatsItemBinding): RecyclerView.ViewHolder(databinding.root) {
    val date = databinding.dateTextView
    val setName = databinding.careerStatsTeamNameTextView
    val apps = databinding.careerAppsTextView
    val goals = databinding.careerGoalsTextView
    val assists = databinding.careerAssistsTextView
}