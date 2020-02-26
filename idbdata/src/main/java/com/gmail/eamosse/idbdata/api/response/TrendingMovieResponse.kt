package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Movie

internal data class TrendingMovieResponse(
    val page: Int,
    val results: List<Result>
) {
    data class Result(
        val adult: Boolean,
        val backdrop_path: String,
        val genre_ids: List<Int>,
        val id: Int,
        val original_language: String,
        val original_title: String,
        val overview: String,
        val popularity: Double,
        val poster_path: String,
        val release_date: String,
        val title: String,
        val video: Boolean,
        val vote_average: Double,
        val vote_count: Int
    )
}

internal fun TrendingMovieResponse.Result.toTrending() = Movie(
    adult = adult,
    original_title = original_title,
    title = title,
    backdrop_path = backdrop_path,
    poster_path = poster_path,
    id = id,
    overview = overview,
    release_date = release_date
)