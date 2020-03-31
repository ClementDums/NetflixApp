package com.gmail.eamosse.idbdata.data

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val adult: Boolean,
    val backdrop_path: String?,
    val original_title: String,
    val release_date: String?,
    val genre_ids: List<Int>?,
    val original_language: String,
    val popularity: Double,
    val production_companies: List<ProductionCompany>?,
    val video: Boolean,
    val vote_average: String,
    val vote_count: String,
    var videos: List<Video>?
)