package com.gmail.eamosse.idbdata.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val adult: Boolean,
    val backdrop_path: String?,
    val original_title: String,
    val release_date: String?
) : Parcelable