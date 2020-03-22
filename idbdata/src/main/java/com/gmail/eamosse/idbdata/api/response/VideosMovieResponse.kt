package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.data.ProductionCompany
import com.gmail.eamosse.idbdata.data.Video

internal data class VideosMovieResponse(
    val id: Int,
    val results: List<Result>
) {
    data class Result(
        val id: String,
        val key: String
    )
}

internal fun VideosMovieResponse.Result.toVideo() = Video(
    id = id,
    key = key
)