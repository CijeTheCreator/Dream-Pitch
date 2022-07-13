package com.cijei.dreampitch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.cijei.dreampitch.R

class TeamDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_details)

        val topMenu = TeamDetailTopMenuFragment()
        val fm: FragmentManager = supportFragmentManager
        fm.beginTransaction().replace(R.id.header_fragment, topMenu).commit()



    }
}