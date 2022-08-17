package com.cijei.dreampitch.data

import android.os.Parcel
import android.os.Parcelable
import java.util.*
import kotlin.collections.ArrayList

data class Game(
    var home: Set?,
    var away: Set?,
    var homeScore: Int,
    var awayScore: Int,
    var date: Date?,
    var goals: ArrayList<Goal>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Set::class.java.classLoader),
        parcel.readParcelable(Set::class.java.classLoader),
        parcel.readInt(),
        parcel.readInt(),
        null,
        arrayListOf<Goal>().apply {
            parcel.readList(this, Goal::class.java.classLoader)
        }
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(home, flags)
        parcel.writeParcelable(away, flags)
        parcel.writeInt(homeScore)
        parcel.writeInt(awayScore)
        parcel.writeList(goals)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Game> {
        override fun createFromParcel(parcel: Parcel): Game {
            return Game(parcel)
        }

        override fun newArray(size: Int): Array<Game?> {
            return arrayOfNulls(size)
        }
    }
}