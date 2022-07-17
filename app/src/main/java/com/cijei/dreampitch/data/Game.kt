package com.cijei.dreampitch.data

import java.util.*
import kotlin.collections.ArrayList

data class Game(
    var home: Set?,
    var away: Set?,
    var homeScore: Int,
    var awayScore: Int,
    var date: Date?,
    var goals: ArrayList<Goal>
) {
}