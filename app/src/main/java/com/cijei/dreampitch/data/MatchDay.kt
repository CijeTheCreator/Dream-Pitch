package com.cijei.dreampitch.data

import java.util.*

data class MatchDay(
    var set: Set,
    var date: Date,
    var apps: Int,
    var goals: Int,
    var assists: Int
) {
}