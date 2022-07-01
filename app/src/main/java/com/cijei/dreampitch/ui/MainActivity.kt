package com.cijei.dreampitch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.cijei.dreampitch.R

class MainActivity : AppCompatActivity() {

    private lateinit var bottomMenuFragment: BottomMenuFragment
    private lateinit var mainTimerFragment: TimerFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomMenuFragment = BottomMenuFragment()
        mainTimerFragment = TimerFragment()

        val fm: FragmentManager = supportFragmentManager
        fm.beginTransaction().replace(R.id.timer_fragment, mainTimerFragment).commit()
        fm.beginTransaction().replace(R.id.bottom_menu_fragment, bottomMenuFragment).commit()

    }

    //Intellij debugging video
    //Fix the timer error when I get back from the program
}