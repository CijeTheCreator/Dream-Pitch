package com.cijei.dreampitch.mock

import com.cijei.dreampitch.data.Player
import com.cijei.dreampitch.data.Set

class MockPlayers {

    private lateinit var players: ArrayList<Player>

    fun getPlayers(): ArrayList<Player> {
        val chibuike = Player()
        chibuike.name = "Griezz"
        chibuike.position = "DEF"
        chibuike.club = "ARS"


        val cije = Player()
        cije.name = "Ceejay"
        cije.position = "MID"
        cije.club = "LIV"

        val vlad = Player()
        vlad.name = "Vlad"
        vlad.position = "MID"
        vlad.club = "MCI"


        val honochie = Player()
        honochie.name = "Onochie"
        honochie.club = "MNU"
        honochie.position = "ATT"

        players = ArrayList<Player>(4)
        players.add(chibuike)
        players.add(cije)
        players.add(honochie)
        players.add(vlad)

        return players
    }

    fun addPlayer(name: String, position: String, club: String) {
        val player = Player()
        player.name = name
        player.position = position
        player.club = club
        players.add(player)
    }

}



class MockSets {

    private lateinit var sets: ArrayList<Set>

    fun getSets(): ArrayList<Set> {
        val teamCije = Set()
        teamCije.teamName = "Team Cije"
        teamCije.wins = 5
        teamCije.draws = 4
        teamCije.loss = 3

        val teamGriezz = Set()
        teamGriezz.teamName = "Team Griezz"
        teamGriezz.wins = 6
        teamGriezz.draws = 7
        teamGriezz.loss = 9

        val teamVlad = Set()
        teamVlad.wins = 6
        teamVlad.draws = 7
        teamVlad.loss = 9
        teamVlad.teamName = "Team Vlad"

        sets = ArrayList<Set>()
        sets.add(teamCije)
        sets.add(teamGriezz)
        sets.add(teamVlad)

        return sets
    }

    fun addSet(players: ArrayList<Player>) {
        val player1Name = players[0].name
        val setName = "Team $player1Name"
        val newSet = Set()
        newSet.teamName = setName
        newSet.wins = 0
        newSet.draws = 0
        newSet.loss = 0
        sets.add(newSet)
    }
}