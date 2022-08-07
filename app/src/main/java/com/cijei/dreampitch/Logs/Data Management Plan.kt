package com.cijei.dreampitch.Logs

/*
There will be a seperate database for Event, FantasyData, FantasyPlayer, FantasyScore, Games, MatchDay, Player, Set, Stat, WeekData
Meaning they should have primary keys and secondary keys for easy manipulation
 */

/*
Differentiate what is permanent from what is not

//Permanent
****************
Stat
Player

****************

//Temporary
****************
WeekData
FantasyData
FantasyPlayer
FantasyScore
Set
MatchDay
****************

//In between
****************
Event
Game
****************
 */