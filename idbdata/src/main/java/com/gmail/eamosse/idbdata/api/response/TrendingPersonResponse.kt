package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Person
import com.google.gson.annotations.SerializedName

internal data class TrendingPersonResponse(
    val page: Int,
    val results: List<Person>
) {
    data class Person(
        val adult: Boolean,
        val gender: Int,
        val id: Int,
        @SerializedName("known_for")
        val knownFor: List<KnownFor>,
        @SerializedName("known_for_department")
        val knownForDepartment: String,
        @SerializedName("media_type")
        val mediaType: String,
        val name: String,
        val popularity: Double,
        @SerializedName("profile_path")
        val profilePath: String
    )

    data class KnownFor(
        val adult: Boolean,
        @SerializedName("backdrop_path")
        val backdropPath: String,
        @SerializedName("genre_ids")
        val genreIds: List<Int>,
        val id: Int,
        @SerializedName("media_type")
        val mediaType: String,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("original_title")
        val originalTitle: String,
        val overview: String,
        val popularity: Double,
        @SerializedName("poster_path")
        val posterPath: String,
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

internal fun TrendingPersonResponse.Person.toPerson() = Person(
    adult = adult,
    id = id,
    name = name,
    gender = gender,
    popularity = popularity,
    profile_path = profilePath
)