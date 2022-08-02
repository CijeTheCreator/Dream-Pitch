package com.cijei.dreampitch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.cijei.dreampitch.R
import com.cijei.dreampitch.mock.MockMatches

class MatchDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_details)


        val matchDetailTopMenuFragment = MatchDetailTopMenu()
        //TODO("Set the Match onclick")
        val matchDetailBody = MatchDetails(MockMatches().getMatches(null)[0])

        val fm: FragmentManager = supportFragmentManager
        fm.beginTransaction().replace(R.id.match_details_top_menu, matchDetailTopMenuFragment).commit()
        fm.beginTransaction().replace(R.id.match_details_body, matchDetailBody).commit()


    }


}