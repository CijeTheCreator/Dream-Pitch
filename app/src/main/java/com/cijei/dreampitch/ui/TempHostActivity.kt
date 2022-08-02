package com.cijei.dreampitch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cijei.dreampitch.R
import com.cijei.dreampitch.mock.MockPlayers

class TempHostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temp_host)

        val data = intent.extras

        val statsFragment = StatsFragment()
        val fantasyHomeFragment = FantasyHomeFragment()
        val fantasyPointsFragment = FantasyPointsFragment(MockPlayers().getPlayers())
        fantasyPointsFragment.arguments = data
        val fm = supportFragmentManager
        fm.beginTransaction().replace(R.id.stats, fantasyPointsFragment).commit()
    }
}