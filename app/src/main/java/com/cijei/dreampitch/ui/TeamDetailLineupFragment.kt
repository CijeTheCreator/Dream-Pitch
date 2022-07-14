package com.cijei.dreampitch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.cijei.dreampitch.R
import com.cijei.dreampitch.data.Set

class TeamDetailLineupFragment(val set: Set): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.match_detail_lineup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val strikerImageView = view.findViewById<ImageView>(R.id.strikerImageView)
        val defenderImageView = view.findViewById<ImageView>(R.id.defenderImageView)
        val midfielder1ImageView = view.findViewById<ImageView>(R.id.midfielder1ImageView)
        val midfielder2ImageView = view.findViewById<ImageView>(R.id.midfielder2ImageView)
        val goalieImageView = view.findViewById<ImageView>(R.id.goalKeeper)

        val strikerTextView = view.findViewById<TextView>(R.id.strikerTextView)
        val defenderTextView = view.findViewById<TextView>(R.id.defenderTextView)
        val midfielder1TextView = view.findViewById<TextView>(R.id.midfielder1TextView)
        val midfielder2TextView = view.findViewById<TextView>(R.id.midfielder2TextView)
        val goalieTextView = view.findViewById<TextView>(R.id.goalKeeperTextView)


    }
}