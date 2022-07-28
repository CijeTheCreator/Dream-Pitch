package com.cijei.dreampitch.mock

import com.cijei.dreampitch.data.*
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

        val emirates = Player()
        emirates.name = "Emirates"
        emirates.club = "CHE"
        emirates.position = "DEF"

        players = ArrayList<Player>(4)
        players.add(chibuike)
        players.add(cije)
        players.add(honochie)
        players.add(vlad)
        players.add(emirates)

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

        val players = MockPlayers().getPlayers()
        val teamCije = Set()
        teamCije.teamName = "Team Cije"
        teamCije.wins = 5
        teamCije.draws = 4
        teamCije.loss = 3
        teamCije.players = players


        val teamGriezz = Set()
        teamGriezz.teamName = "Team Griezz"
        teamGriezz.wins = 6
        teamGriezz.draws = 7
        teamGriezz.loss = 9
        teamGriezz.players = players

        val teamVlad = Set()
        teamVlad.wins = 6
        teamVlad.draws = 7
        teamVlad.loss = 9
        teamVlad.teamName = "Team Vlad"
        teamVlad.players = players

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



        val game1 = Game(sets[1], sets[2], randomScoreGen(), randomScoreGen(), Date(2022, 1, 22), ArrayList<Goal>())
        val game2 = Game(sets[0], sets[1], randomScoreGen(), randomScoreGen(), Date(2022, 1, 22),  ArrayList<Goal>())
        val game3 = Game(sets[0], sets[2], randomScoreGen(), randomScoreGen(), Date(2022, 1, 22),  ArrayList<Goal>())
        val game4 = Game(sets[1], sets[0], randomScoreGen(), randomScoreGen(), Date(2022, 2, 22),  ArrayList<Goal>())
        val game5 = Game(sets[2], sets[0], randomScoreGen(), randomScoreGen(), Date(2022, 2, 22),  ArrayList<Goal>())
        val game6 = Game(sets[2], sets[1], randomScoreGen(), randomScoreGen(), Date(2022, 2, 22),  ArrayList<Goal>())
        val game7 = Game(sets[0], sets[2], randomScoreGen(), randomScoreGen(), Date(2022, 3, 22),  ArrayList<Goal>())
        val game8 = Game(sets[1], sets[0], randomScoreGen(), randomScoreGen(), Date(2022, 3, 22),  ArrayList<Goal>())
        val game9 = Game(sets[1], sets[0], randomScoreGen(), randomScoreGen(), Date(2022, 3, 22),  ArrayList<Goal>())

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

        for (game in games) {
            val homeOrAway = Random.nextInt(0, 2)
        if (homeOrAway == 0) {
            val randomScorer = Random.nextInt(0, 5)
            val randomAssist = Random.nextInt(0, 5)
            val randomTime = Random.nextInt(0, 11)

            val goal = Goal(game.home?.players?.get(randomScorer), game.home?.players?.get(randomAssist), randomTime, "home")
            game.goals.add(goal)
        } else {
            val randomScorer = Random.nextInt(0, 5)
            val randomAssist = Random.nextInt(0, 5)
            val randomTime = Random.nextInt(0, 11)

            val goal = Goal(game.away?.players?.get(randomScorer), game.away?.players?.get(randomAssist), randomTime, "away")
            game.goals = ArrayList<Goal>()
            game.goals.add(goal)
        }
        }


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

class MockStats() {
    fun getMockStats(player: Player): Stat {
        val appearances = Random.nextInt(2, 50)
        val goals = Random.nextInt(2, 100)
        val wins = Random.nextInt(2, 50)
        val losses = Random.nextInt(2, 50)

        val date = Date(2001, 9, 19)

        val matchDays = ArrayList<MatchDay>()
        for (i in 0..20){
            val apps = Random.nextInt(1, 20)
            val gs = Random.nextInt(0, apps)
            val ass = Random.nextInt(0, apps)
            val set = MockSets().getSets()[Random.nextInt(0, 3)]
            val matchDay = MatchDay(set, date, apps, gs, ass)
            matchDays.add(matchDay)
        }

        val stat = Stat(appearances, goals, wins, losses, matchDays)
        return stat
    }
}

class MockFantasyData() {
    fun createFantasyData(player: Player): FantasyData {
        val score = Random.nextInt(23, 100)
        val date = Date(2022, 7, 22)

        val weekData = WeekData(score, date)
        return FantasyData(score, arrayListOf(weekData), player)
    }

    fun getFantasyData(): ArrayList<FantasyData> {
        val players = MockPlayers().getPlayers()
        val fantasyDatas = ArrayList<FantasyData>()
        for (player in players) {
            val fantasyData = createFantasyData(player)
            fantasyDatas.add(fantasyData)
        }
        return fantasyDatas
    }
}

class MockFantasyScores(val players: ArrayList<Player>) {
    fun getFantasyScores(): ArrayList<FantasyScore> {
        val fantasyScores = ArrayList<FantasyScore>()
        for (player in players) {
            val score = Random.nextInt(2, 30)
            val fantasyScore = FantasyScore(player, score)
            fantasyScores.add(fantasyScore)
        }
        return fantasyScores
    }
}
