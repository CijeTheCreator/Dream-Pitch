package com.cijei.dreampitch.data

import com.google.firebase.database.*
import kotlinx.coroutines.*
import java.time.Instant
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class Database {
    private lateinit var database: DatabaseReference
//    private lateinit var matchesByDate: ArrayList<ArrayList<Game>>

    suspend fun getMatchesByDate(): ArrayList<ArrayList<Game>>{
        database = FirebaseDatabase.getInstance("https://dream-pitch-default-rtdb.firebaseio.com/").getReference("Games")
        var matches = ArrayList<Game>()
        val listener = object: ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {
                val gamesdb = p0.children
                val games = ArrayList<Game>()
                for (gameDb in gamesdb) {
                    val awayScore = gameDb.child("awayScore").value as Long
                    val homeScore = gameDb.child("homeScore").value as Long

                    val homeSet = Set()
                    val homedraws = gameDb.child("home").child("draws").value as Long
                    val homewins = gameDb.child("home").child("wins").value as Long
                    val homeloss = gameDb.child("home").child("loss").value as Long
                    val homeTeamName = gameDb.child("home").child("teamName").value as String
                    homeSet.wins = homewins.toInt()
                    homeSet.loss = homeloss.toInt()
                    homeSet.draws = homedraws.toInt()
                    homeSet.teamName = homeTeamName

                    val homeplayersdb = gameDb.child("home").child("players").children

                    val homePlayers = ArrayList<Player>()
                    for (playerdb in homeplayersdb) {
                        val playerName = playerdb.child("name").value as String
                        val playerClub = playerdb.child("club").value as String
                        val playerPosition = playerdb.child("position").value as String
                        val player = Player()
                        player.position = playerPosition
                        player.club = playerClub
                        player.name = playerName

                        homePlayers.add(player)
                    }


                    val awaySet = Set()
                    val awayDraws = gameDb.child("away").child("draws").value as Long
                    val awaywins = gameDb.child("away").child("wins").value as Long
                    val awayloss = gameDb.child("away").child("loss").value as Long
                    val awayTeamName = gameDb.child("away").child("teamName").value as String
                    awaySet.wins = awaywins.toInt()
                    awaySet.loss = awayloss.toInt()
                    awaySet.draws = awayDraws.toInt()
                    awaySet.teamName = awayTeamName

                    val awayPlayersDb = gameDb.child("away").child("players").children

                    val awayPlayers = ArrayList<Player>()
                    for (playerdb in awayPlayersDb) {
                        val playerName = playerdb.child("name").value as String
                        val playerClub = playerdb.child("club").value as String
                        val playerPosition = playerdb.child("position").value as String
                        val player = Player()
                        player.position = playerPosition
                        player.club = playerClub
                        player.name = playerName

                        awayPlayers.add(player)
                    }

                    val dateDb = gameDb.child("date").child("date").value as Long
                    val dateDbMonth = gameDb.child("date").child("date").value as Long
                    val dateDbYear = gameDb.child("date").child("date").value as Long


                    val date = Date(dateDb.toInt(), dateDbMonth.toInt(), dateDbYear.toInt())

                    val goalsdb = gameDb.child("goals").children
                    val goals = ArrayList<Goal>()
                    for (goaldb in goalsdb) {
                        val scorerName = goaldb.child("scorer").child("name").value as String
                        val scorerPosition = goaldb.child("scorer").child("position").value as String
                        val scorerClub = goaldb.child("scorer").child("club").value as String
                        val scorerPlayer = Player()
                        scorerPlayer.name = scorerName
                        scorerPlayer.club = scorerClub
                        scorerPlayer.position = scorerPosition


                        val assistorName = goaldb.child("assist").child("name").value as String
                        val assistorPosition = goaldb.child("assist").child("position").value as String
                        val assistorClub = goaldb.child("assist").child("club").value as String
                        val assistPlayer = Player()
                        assistPlayer.name = assistorName
                        assistPlayer.club = assistorClub
                        assistPlayer.position = assistorPosition

                        val team = goaldb.child("team").value as String
                        val time = goaldb.child("time").value as Long

                        val goal = Goal(scorerPlayer, assistPlayer, time.toInt(), team)
                        goals.add(goal)
                    }

                    val game = Game(homeSet, awaySet, homeScore.toInt(), awayScore.toInt(), date, goals)
                    games.add(game)
                }
                matches = games
                println(matches)

            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }


        }
        //TODO("Learn some form of asyn/await to call the return here")
        database.addValueEventListener(listener)
        runBlocking {
            delay(1500L)
        }
        return mainCode(matches)
    }

    fun mainCode(matches: ArrayList<Game>): ArrayList<ArrayList<Game>> {
//        val matches = getMatches(null)
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
}