package com.cijei.dreampitch.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cijei.dreampitch.data.Set
import com.cijei.dreampitch.databinding.SetItemBinding
import com.cijei.dreampitch.ui.TeamDetailsActivity
import com.cijei.dreampitch.viewholders.SetsViewHolder
import com.google.android.material.snackbar.Snackbar

class SetsAdapter(private var sets: ArrayList<Set>, val context: Context): RecyclerView.Adapter<SetsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetsViewHolder {
        return SetsViewHolder(SetItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SetsViewHolder, position: Int) {
        val set = sets[position]
        holder.setNameTextView.text = set.teamName
        holder.setWinsTextView.text = set.wins.toString()
        holder.setDrawsTextView.text = set.draws.toString()
        holder.setLossTextView.text = set.loss.toString()

        holder.expandChevron.setOnClickListener {
//            Snackbar.make(holder.expandChevron, "To Set Details", Snackbar.LENGTH_LONG).show()
            val i = Intent(context, TeamDetailsActivity::class.java)
            context.startActivity(i)
        }

    }

    override fun getItemCount(): Int {
        return sets.size
    }

    fun addSet(setz: ArrayList<Set>) {
        sets = setz
        this.notifyDataSetChanged()

    }
}