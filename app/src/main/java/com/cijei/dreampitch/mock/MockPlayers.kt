package com.cijei.dreampitch.mock

import com.cijei.dreampitch.data.Game
import com.cijei.dreampitch.data.Player
import com.cijei.dreampitch.data.Set
import java.time.Instant
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

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

    private var sets: ArrayList<Set> = ArrayList()
    var setz: ArrayList<Set> = ArrayList()

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
        setz = sets
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
        setz.add(newSet)
    }
}

class MockMatches() {

    private var games: ArrayList<Game> = ArrayList()

    fun  getMatches(set: Set?): ArrayList<Game> {
        val mockSets = MockSets()
        val sets = mockSets.getSets()

        val game1 = Game(sets[1], sets[2], randomScoreGen(), randomScoreGen(), Date(2022, 1, 22))
        val game2 = Game(sets[0], sets[1], randomScoreGen(), randomScoreGen(), Date(2022, 1, 22))
        val game3 = Game(sets[0], sets[2], randomScoreGen(), randomScoreGen(), Date(2022, 1, 22))
        val game4 = Game(sets[1], sets[0], randomScoreGen(), randomScoreGen(), Date(2022, 2, 22))
        val game5 = Game(sets[2], sets[0], randomScoreGen(), randomScoreGen(), Date(2022, 2, 22))
        val game6 = Game(sets[2], sets[1], randomScoreGen(), randomScoreGen(), Date(2022, 2, 22))
        val game7 = Game(sets[0], sets[2], randomScoreGen(), randomScoreGen(), Date(2022, 3, 22))
        val game8 = Game(sets[1], sets[0], randomScoreGen(), randomScoreGen(), Date(2022, 3, 22))
        val game9 = Game(sets[1], sets[0], randomScoreGen(), randomScoreGen(), Date(2022, 3, 22))

        val games = ArrayList<Game>()
        games.add(game1)
        games.add(game2)
        games.add(game3)
        games.add(game4)
        games.add(game5)
        games.add(game6)
        games.add(game7)
        games.add(game8)
        games.add(game9)

        return games
    }

    //This function needs more work
    fun getMatchesByDate(): ArrayList<ArrayList<Game>> {
        val matches = getMatches(null)
        val dates = ArrayList<Instant>()
        val matchesByDate = ArrayList<ArrayList<Game>>()

        for (match in matches) {
            val matchDateInstant = match.date?.toInstant()
            if (!dates.contains(matchDateInstant)) {
                if (matchDateInstant != null) {
                    dates.add(matchDateInstant)
                }
            }
        }

        for (date in dates) {
            val gamesByThisDate = ArrayList<Game>()
            for (match in matches) {
                if (match.date?.toInstant() == date) {
                    gamesByThisDate.add(match)
                }
            }
            matchesByDate.add(gamesByThisDate)
        }

        return matchesByDate

    }

    private fun randomScoreGen(): Int {
        val int = (Random.nextInt()/100000000)
        if (int >= 0) {
            return 1
        } else {
            return 0
        }
    }
}