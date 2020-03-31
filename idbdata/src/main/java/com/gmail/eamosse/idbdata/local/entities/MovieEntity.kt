package com.gmail.eamosse.idbdata.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.data.ProductionCompany
import com.gmail.eamosse.idbdata.data.Video

/**
 * Modélise les tokens dans la base de données
 */
@Entity(
    tableName = "idb_movies"
)
internal data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val adult: Boolean,
    val backdrop_path: String?,
    val original_title: String,
    val release_date: String?,
    val original_language: String,
    val popularity: Double,
    val video: Boolean,
    val vote_average: String,
    val vote_count: String
)

internal fun MovieEntity.toMovie() = Movie(
    id = id,
    title = title,
    overview = overview,
    poster_path = poster_path,
    adult = adult,
    backdrop_path = backdrop_path,
    original_title = original_title,
    release_date = release_date,
    genre_ids = null,
    original_language = original_language,
    popularity = popularity,
    production_companies = null,
    video = video,
    vote_average = vote_average,
    vote_count = vote_count,
    videos = null
)