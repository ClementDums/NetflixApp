package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.data.ProductionCompany
import com.google.gson.annotations.SerializedName

internal data class TrendingMovieResponse(
    val page: Int,
    val results: List<Result>
) {
    data class Result(
        val adult: Boolean,
        @SerializedName("backdrop_path")
        val backdropPath: String,
        @SerializedName("genre_ids")
        val genreIds: List<Int>?,
        val id: Int,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("original_title")
        val originalTitle: String,
        val overview: String,
        val popularity: Double,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("production_companies")
        val productionCompanies: List<ProductionCompany>?,
        @SerializedName("release_date")
        val releaseDate: String,
        val title: String,
        val video: Boolean,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
        val voteCount: Int
    )
}

internal fun TrendingMovieResponse.Result.toTrending() = Movie(
    adult = adult,
    original_title = originalTitle,
    title = title,
    backdrop_path = backdropPath,
    poster_path = posterPath,
    id = id,
    overview = overview,
    release_date = releaseDate,
    genre_ids = genreIds,
    original_language = originalLanguage,
    popularity = popularity,
    production_companies = productionCompanies,
    video = video,
    vote_average = voteAverage.toString(),
    vote_count = voteCount.toString(),
    videos = null
)