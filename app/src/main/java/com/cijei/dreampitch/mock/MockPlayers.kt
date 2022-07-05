package com.cijei.dreampitch.mock

import com.cijei.dreampitch.data.Player
import com.cijei.dreampitch.data.Set

abstract class MockPlayers {
    fun getPlayers(): ArrayList<Player> {
        val chibuike = Player("Griezz", "DEF", "ARS")
        val cije = Player("Ceejay", "MID", "LIV")
        val vlad = Player("Vlad", "MID", "MCI")
        val honochie = Player("Onochie", "ATT", "MNU")
        val players = ArrayList<Player>(4)
        players.add(chibuike)
        players.add(cije)
        players.add(honochie)
        players.add(vlad)


        return players
    }

}



abstract class MockSets {
    fun getSets(): ArrayList<Set> {
        val teamCije = Set("Team Cije", 2, 3, 4)
        val teamGriezz = Set("Team Griezz", 5, 6, 8)
        val teamVlad = Set("Team Vlad", 8, 0, 0)

        val sets = ArrayList<Set>()
        sets.add(teamCije)
        sets.add(teamGriezz)
        sets.add(teamVlad)

        return sets
    }
}