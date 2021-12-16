package com.fsociety.moviesapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseMovie(

    @SerializedName("results")
    val movies: List<Movie>

):Parcelable{
    constructor(): this(mutableListOf())
}
