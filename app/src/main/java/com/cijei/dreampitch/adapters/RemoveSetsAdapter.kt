package com.cijei.dreampitch.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.data.Set
import com.cijei.dreampitch.databinding.RemoveSetItemBinding
import com.cijei.dreampitch.viewholders.RemoveSetsViewHolder

class RemoveSetsAdapter(private var setz: ArrayList<Set>): RecyclerView.Adapter<RemoveSetsViewHolder>() {
    var selectedSets: ArrayList<Set> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemoveSetsViewHolder {
        return RemoveSetsViewHolder(RemoveSetItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RemoveSetsViewHolder, position: Int) {
        holder.setNameTextView.text = setz[position].teamName

        holder.removeSetItem.setOnClickListener {
            if (!selectedSets.contains(setz[position])) {
                selectedSets.add(setz[position])
                holder.tick.visibility = View.VISIBLE
            } else {
                selectedSets.remove(setz[position])
                holder.tick.visibility = View.INVISIBLE
            }
            println(selectedSets)
        }

    }

    override fun getItemCount(): Int {
        return setz.size
    }

}