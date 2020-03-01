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
    val release_date: String?,
    val genre_ids: List<Int>,
    val original_language: String,
    val popularity: String,
    val video: Boolean,
    val vote_average: String,
    val vote_count: String
) : Parcelable