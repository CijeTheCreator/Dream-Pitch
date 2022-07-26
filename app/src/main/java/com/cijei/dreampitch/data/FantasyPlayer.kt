package com.cijei.dreampitch.data

import android.widget.LinearLayout
import android.widget.TextView

class FantasyPlayer(
    var playee: LinearLayout,
    var playerScore: TextView,
    var playerName: TextView
) {
    fun setNameAndScore(fantasyScore: FantasyScore){
        val name = fantasyScore.player.name
        val score = fantasyScore.score
        playerName.text = name
        playerScore.text = score.toString()
    }
}