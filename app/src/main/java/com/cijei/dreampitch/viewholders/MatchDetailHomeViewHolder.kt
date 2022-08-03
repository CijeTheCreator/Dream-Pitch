package com.cijei.dreampitch.viewholders

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.data.Goal
import com.cijei.dreampitch.databinding.MatchDetailItemHomeBinding
import com.cijei.dreampitch.ui.PlayerDetailsActivity

class MatchDetailHomeViewHolder(var databinding: MatchDetailItemHomeBinding): RecyclerView.ViewHolder(databinding.root) {
    fun bind(goal: Goal, context: Context) {
        databinding.timeTextView.text = "${goal.time}'"
        databinding.goalScorerTextView.text = goal.scorer?.name
        databinding.assistPlayerTextView.text = goal.assist?.name
        databinding.newScoreTextView.text = "1-0" //TODO("Work on this Later")
        databinding.event.setOnClickListener {
            val i = Intent(context, PlayerDetailsActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable("genetics", goal.scorer)
            i.putExtras(bundle)
            context.startActivity(i)
        }
    }
}