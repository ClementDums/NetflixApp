package com.gmail.eamosse.idbdata.data

import com.gmail.eamosse.idbdata.local.entities.MovieEntity

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

internal fun Movie.toEntityMovie() = MovieEntity(
    id = id,
    title = title,
    overview = overview,
    poster_path = poster_path,
    adult = adult,
    backdrop_path = backdrop_path,
    original_title = original_title,
    release_date = release_date,
    original_language = original_language,
    popularity = popularity,
    video = video,
    vote_average = vote_average,
    vote_count = vote_count
)