package com.cijei.dreampitch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.data.MatchDay
import com.cijei.dreampitch.databinding.CareerStatsItemBinding
import com.cijei.dreampitch.viewholders.CareerStatsViewHolder

class CareerStatsAdapter(val matchdays: ArrayList<MatchDay>): RecyclerView.Adapter<CareerStatsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CareerStatsViewHolder {
        return CareerStatsViewHolder(CareerStatsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CareerStatsViewHolder, position: Int) {
        val matchday = matchdays[position]
        holder.apps.text = matchday.apps.toString()
        holder.assists.text = matchday.assists.toString()
        holder.date.text = matchday.date.toString()
        holder.goals.text = matchday.goals.toString()
        holder.setName.text = matchday.set.teamName
    }

    override fun getItemCount(): Int {
        return matchdays.size
    }
}