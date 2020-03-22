package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.data.ProductionCompany
import com.google.gson.annotations.SerializedName

internal data class MovieResponse(
    @SerializedName("results")
    val results: List<MovieItem>
) {
    data class MovieItem(
        val adult: Boolean,
        val backdrop_path: String?,
        val genre_ids: List<Int>?,
        val id: Int,
        val original_language: String,
        val original_title: String,
        val overview: String,
        val popularity: Double,
        val production_companies: List<ProductionCompany>?,
        val poster_path: String?,
        val release_date: String?,
        val title: String,
        val video: Boolean,
        val vote_average: Double,
        val vote_count: Int
    ) {
        fun toMovie(): Movie {
            return Movie(
                adult = adult,
                original_title = original_title,
                title = title,
                backdrop_path = backdrop_path,
                poster_path = poster_path,
                id = id,
                overview = overview,
                release_date = release_date, genre_ids = genre_ids,
                original_language = original_language,
                popularity = popularity,
                production_companies = production_companies,
                video = video,
                vote_average = vote_average.toString(),
                vote_count = vote_count.toString(),
                videos = null
            )
        }
    }

}