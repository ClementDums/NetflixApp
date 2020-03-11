package com.gmail.eamosse.idbdata.api.service

import com.gmail.eamosse.idbdata.api.response.*
import com.gmail.eamosse.idbdata.api.response.CategoryResponse
import com.gmail.eamosse.idbdata.api.response.MovieResponse
import com.gmail.eamosse.idbdata.api.response.TokenResponse
import com.gmail.eamosse.idbdata.api.response.TrendingPersonResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

internal interface MovieService {
    @GET("authentication/token/new")
    suspend fun getToken(): Response<TokenResponse>

    @POST("authentication/session/new")
    suspend fun getSession(@Body token:String):Response<SessionResponse>

    @GET("genre/movie/list")
    suspend fun getCategories(): Response<CategoryResponse>

    @GET("discover/movie")
    suspend fun getDiscover(@Query("with_genres") withGenres:Int): Response<MovieResponse>

    @GET("trending/movie/week")
    suspend fun getTrendingMovie(): Response<TrendingMovieResponse>

    @GET("trending/person/week")
    suspend fun getTrendingPeople(): Response<TrendingPersonResponse>

    @POST("movie/{id}/rating")
    suspend fun rateMovie(@Body note:Float, @Path("id")id: Int?)
}