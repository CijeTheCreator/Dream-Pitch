package com.cijei.dreampitch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cijei.dreampitch.R

class TimerFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,C
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_timer_fragment, container, false)
    }
}