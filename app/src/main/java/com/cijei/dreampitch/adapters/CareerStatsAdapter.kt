package com.cijei.dreampitch.adapters

import android.annotation.SuppressLint
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

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CareerStatsViewHolder, position: Int) {
        val matchday = matchdays[position]
        val date = matchday.date
        holder.apps.text = (matchday.set.wins + matchday.set.loss).toString()
        holder.assists.text = matchday.assists.toString()
        holder.date.text = "${date.year}/${date.month}/${date.year}"
        holder.goals.text = matchday.goals.toString()
        holder.setName.text = matchday.set.teamName
    }

    override fun getItemCount(): Int {
        return matchdays.size
    }
}