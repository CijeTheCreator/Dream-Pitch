package com.cijei.dreampitch.data

import android.os.Parcel
import android.os.Parcelable

class Player() : Parcelable {
    var name: String = ""
    var position: String = ""
    var club: String = ""

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()?: ""
        position = parcel.readString()?: ""
        club = parcel.readString()?: ""
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(position)
        parcel.writeString(club)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Player> {
        override fun createFromParcel(parcel: Parcel): Player {
            return Player(parcel)
        }

        override fun newArray(size: Int): Array<Player?> {
            return arrayOfNulls(size)
        }
    }
}
