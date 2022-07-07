package com.cijei.dreampitch.mock

import com.cijei.dreampitch.data.Player
import com.cijei.dreampitch.data.Set

class MockPlayers {

    private lateinit var players: ArrayList<Player>

    fun getPlayers(): ArrayList<Player> {
        val chibuike = Player("Griezz", "DEF", "ARS")
        val cije = Player("Ceejay", "MID", "LIV")
        val vlad = Player("Vlad", "MID", "MCI")
        val honochie = Player("Onochie", "ATT", "MNU")
        players = ArrayList<Player>(4)
        players.add(chibuike)
        players.add(cije)
        players.add(honochie)
        players.add(vlad)

        return players
    }

    fun addPlayer(name: String, position: String, club: String) {
        val player = Player(name, position, club)
        players.add(player)
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