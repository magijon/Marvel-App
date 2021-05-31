package com.mebeal.marvelapp.presentation.model

import android.os.Parcel
import android.os.Parcelable

data class CharactersDisplayModel(
    val id: Int,
    val description: String? = null,
    val name: String? = null,
    val path: String? = null,
    val extension: String? = null,
    val date: String? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(description)
        parcel.writeString(name)
        parcel.writeString(path)
        parcel.writeString(extension)
        parcel.writeString(date)
    }

    companion object CREATOR : Parcelable.Creator<CharactersDisplayModel> {

        override fun createFromParcel(parcel: Parcel) =  CharactersDisplayModel(parcel)

        override fun newArray(size: Int): Array<CharactersDisplayModel?> = arrayOfNulls(size)

    }
}