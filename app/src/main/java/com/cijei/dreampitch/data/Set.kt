package com.cijei.dreampitch.data

import android.os.Parcel
import android.os.Parcelable

class Set() : Parcelable{
    var teamName: String = ""
    var wins: Int = 0
    var draws: Int = 0
    var loss: Int = 0
    var players: ArrayList<Player> = ArrayList()

    constructor(parcel: Parcel) : this() {
        teamName = parcel.readString().toString()
        wins = parcel.readInt()
        draws = parcel.readInt()
        loss = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(teamName)
        parcel.writeInt(wins)
        parcel.writeInt(draws)
        parcel.writeInt(loss)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Set> {
        override fun createFromParcel(parcel: Parcel): Set {
            return Set(parcel)
        }

        override fun newArray(size: Int): Array<Set?> {
            return arrayOfNulls(size)
        }
    }
}
