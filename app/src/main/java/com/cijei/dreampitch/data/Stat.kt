package com.cijei.dreampitch.data

data class Stat(
    var appearances: Int,
    var goals: Int,
    var wins: Int,
    var assists: Int,
    var losses: Int,
    var matchDays: ArrayList<MatchDay>,
) {
}