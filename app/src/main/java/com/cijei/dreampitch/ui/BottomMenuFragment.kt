package com.cijei.dreampitch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.cijei.dreampitch.R

class BottomMenuFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_menu_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val setsTextView = view.findViewById<TextView>(R.id.sets_text_view)
        val statsTextView = view.findViewById<TextView>(R.id.stats_text_view)
        val fantasyTextView = view.findViewById<TextView>(R.id.fantasy_text_view)

        setsTextView.setOnClickListener {
            val setsFragment = SetsFragment(null)
//            val mainTimerFragment = TimerFragment()
            val fm = activity?.supportFragmentManager
            fm?.beginTransaction()?.replace(R.id.timer_fragment, setsFragment)?.commit()
            //TODO("Bolden and Underline it")
        }

        statsTextView.setOnClickListener {
            val statsFragment = StatsFragment()
            val fm = activity?.supportFragmentManager
            fm?.beginTransaction()?.replace(R.id.timer_fragment, statsFragment)?.commit()
            //TODO("Bolden and Underline it")

        }

        fantasyTextView.setOnClickListener {
            val fantasyHomeFragment = FantasyHomeFragment()
            val fm = activity?.supportFragmentManager
            fm?.beginTransaction()?.replace(R.id.timer_fragment, fantasyHomeFragment)?.commit()
            //TODO("Bolden and Underline it")

        }
    }
}