package com.example.movielist.domain.model

import android.os.Parcel
import android.os.Parcelable
import java.lang.IllegalArgumentException

data class MovieList(
    val adult: Boolean,
    val originalLanguage: String,
    val posterPath: String,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val voteAverage: Double
): Parcelable {
    constructor(parcel: Parcel): this(
        parcel.readByte()!=0.toByte(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeByte(if (adult) 1 else 0)
        dest.writeString(originalLanguage)
        dest.writeString(posterPath)
        dest.writeString(title)
        dest.writeString(overview)
        dest.writeString(releaseDate)
        dest.writeDouble(voteAverage)
    }

    override fun describeContents(): Int {
        return 0
    }



    companion object CREATOR : Parcelable.Creator<MovieList> {
        override fun createFromParcel(parcel: Parcel?): MovieList {
            return if(parcel!=null)
                MovieList(parcel)
            else
                throw IllegalArgumentException("Parcel Cannot be Null")
        }

        override fun newArray(size: Int): Array<MovieList?> {
            return arrayOfNulls(size)
        }
    }
}