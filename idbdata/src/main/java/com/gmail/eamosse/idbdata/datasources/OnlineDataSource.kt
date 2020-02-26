package com.gmail.eamosse.idbdata.datasources

import com.gmail.eamosse.idbdata.api.response.*
import com.gmail.eamosse.idbdata.api.response.CategoryResponse
import com.gmail.eamosse.idbdata.api.response.MovieResponse
import com.gmail.eamosse.idbdata.api.response.TokenResponse
import com.gmail.eamosse.idbdata.api.response.TrendingMovieResponse
import com.gmail.eamosse.idbdata.api.service.MovieService
import com.gmail.eamosse.idbdata.utils.Result
import com.gmail.eamosse.idbdata.utils.parse
import com.gmail.eamosse.idbdata.utils.safeCall
import retrofit2.Response

/**
 * Manipule les données de l'application en utilisant un web service
 * Cette classe est interne au module, ne peut être initialisé ou exposé aux autres composants
 * de l'application
 */
internal class OnlineDataSource(private val service: MovieService) {

    /**
     * Récupérer le token du serveur
     * @return [Result<Token>]
     * Si [Result.Succes], tout s'est bien passé
     * Sinon, une erreur est survenue
     */
    suspend fun getToken(): Result<TokenResponse> {
        return safeCall {
            val response = service.getToken()
            response.parse()
        }
    }

    suspend fun getCategories(): Result<List<CategoryResponse.Genre>> {
        return try {
            val response = service.getCategories()
            if (response.isSuccessful) {
                Result.Succes(response.body()!!.genres)
            } else {
                Result.Error(
                    exception = Exception(),
                    message = response.message(),
                    code = response.code()
                )
            }
        } catch (e: Exception) {
            Result.Error(
                exception = e,
                message = e.message ?: "No message",
                code = -1
            )
        }
    }

    suspend fun getTrendingMovies():Result<List<TrendingMovieResponse.Result>>{
        return safeCall {
            val response:Response<TrendingMovieResponse> = service.getTrendingMovie()
            when(val result:Result<TrendingMovieResponse> = response.parse()){
                is Result.Succes ->Result.Succes(result.data.results)
                is Result.Error -> result
            }
        }
    }

    suspend fun getTrendingPeople():Result<List<TrendingPersonResponse.Person>>{
        return safeCall {
            val response:Response<TrendingPersonResponse> = service.getTrendingPeople()
            when(val result:Result<TrendingPersonResponse> = response.parse()){
                is Result.Succes ->Result.Succes(result.data.results)
                is Result.Error -> result
            }
        }
    }

    suspend fun getMovie(genre_id:Int): Result<List<MovieResponse.MovieItem>> {
        return try {
            val response = service.getDiscover(genre_id)
            if (response.isSuccessful) {
                Result.Succes(response.body()!!.results)
            } else {
                Result.Error(
                    exception = Exception(),
                    message = response.message(),
                    code = response.code()
                )
            }
        } catch (e: Exception) {
            Result.Error(
                exception = e,
                message = e.message ?: "No message",
                code = -1
            )
        }
    }
}

