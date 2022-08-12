package com.cijei.dreampitch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.cijei.dreampitch.R
import com.cijei.dreampitch.data.Set
import com.cijei.dreampitch.mock.MockSets

class TeamDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_details)

        val topMenu = TeamDetailTopMenuFragment()
        //TODO("Use the set from the previous activity")
        val set = intent.extras?.get("set") as Set
        val teamDetailsLineupFragment = TeamDetailLineupFragment(set)
        val fm: FragmentManager = supportFragmentManager


        fm.beginTransaction().replace(R.id.header_fragment, topMenu).commit()
        fm.beginTransaction().replace(R.id.recycler_views_fragment, teamDetailsLineupFragment).commit()

    }
}