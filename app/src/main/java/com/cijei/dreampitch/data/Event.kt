package com.cijei.dreampitch.data

import android.os.Parcel
import android.os.Parcelable

data class Goal(
    var scorer: Player?,
    var assist: Player?,
    var time: Int?,
    var team: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Player::class.java.classLoader),
        parcel.readParcelable(Player::class.java.classLoader),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(scorer, flags)
        parcel.writeParcelable(assist, flags)
        parcel.writeValue(time)
        parcel.writeString(team)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Goal> {
        override fun createFromParcel(parcel: Parcel): Goal {
            return Goal(parcel)
        }

        override fun newArray(size: Int): Array<Goal?> {
            return arrayOfNulls(size)
        }
    }

}