package com.cijei.dreampitch.data

import java.util.*

data class Game(
    var home: Set?,
    var away: Set?,
    var homeScore: Int?,
    var awayScore: Int?,
    var date: Date?
) {
}