package com.cijei.dreampitch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cijei.dreampitch.R

class TempHostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temp_host)

        val statsFragment = StatsFragment()
        val fantasyHomeFragment = FantasyHomeFragment()
        val fm = supportFragmentManager
        fm.beginTransaction().replace(R.id.stats, fantasyHomeFragment).commit()
    }
}