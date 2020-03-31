package com.gmail.eamosse.idbdata.repository

import com.gmail.eamosse.idbdata.api.response.*
import com.gmail.eamosse.idbdata.api.response.toEntity
import com.gmail.eamosse.idbdata.api.response.toPerson
import com.gmail.eamosse.idbdata.api.response.toToken
import com.gmail.eamosse.idbdata.api.response.toTrending
import com.gmail.eamosse.idbdata.data.*
import com.gmail.eamosse.idbdata.datasources.LocalDataSource
import com.gmail.eamosse.idbdata.datasources.OnlineDataSource
import com.gmail.eamosse.idbdata.utils.Result
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * La classe permettant de gérer les données de l'application
 */
class MovieRepository : KoinComponent {
    //Gestion des sources de données locales
    private val local: LocalDataSource by inject()
    //Gestion des sources de données en lignes
    private val online: OnlineDataSource by inject()

    /**
     * Récupérer le token permettant de consommer les ressources sur le serveur
     * Le résultat du datasource est converti en instance d'objets publiques
     */
    suspend fun getToken(): Result<Token> {
        return when (val result = online.getToken()) {
            is Result.Success -> {
                //save the response in the local database
                local.saveToken(result.data.toEntity())
                //return the response
                Result.Success(result.data.toToken())
            }
            is Result.Error -> result

        }
    }

    suspend fun getCategories(): Result<List<Category>> {

        return when (val result = online.getCategories()) {
            is Result.Success -> {
                // On utilise la fonction map pour convertir les catégories de la réponse serveur
                // en liste de categories d'objets de l'application
                val categories = result.data.map {
                    it.toCategory()
                }
                Result.Success(categories)
            }
            is Result.Error -> result
        }
    }

    suspend fun getDiscover(genre_id: Int, pageNumber: Int): Result<List<Movie>> {

        return when (val result = online.getDiscover(genre_id, pageNumber)) {
            is Result.Success -> {
                // On utilise la fonction map pour convertir les catégories de la réponse serveur
                // en liste de categories d'objets de l'application
                val movies = result.data.map {
                    println(it)
                    it.toMovie()
                }
                Result.Success(movies)
            }
            is Result.Error -> result
        }
    }

    suspend fun getTrendingMovies(): Result<List<Movie>> {

        return when (val result = online.getTrendingMovies()) {
            is Result.Success -> {
                // On utilise la fonction map pour convertir les catégories de la réponse serveur
                // en liste de categories d'objets de l'application
                val movies = result.data.map {
                    it.toTrending()
                }
                Result.Success(movies)
            }
            is Result.Error -> result
        }
    }

    suspend fun getTrendingPeople(): Result<List<Person>> {

        return when (val result = online.getTrendingPeople()) {
            is Result.Success -> {
                // On utilise la fonction map pour convertir les catégories de la réponse serveur
                // en liste de categories d'objets de l'application
                val people = result.data.map {
                    it.toPerson()
                }
                Result.Success(people)
            }
            is Result.Error -> result
        }
    }

    suspend fun getMovieDetails(movie_id: Int): Result<Movie> {

        return when (val result = online.getMovieDetails(movie_id)) {
            is Result.Success -> {
                val movie = result.data.toMovie()
                Result.Success(movie)
            }
            is Result.Error -> result
        }
    }

    suspend fun getMovieVideos(movie_id: Int): Result<List<Video>> {

        return when (val result = online.getMovieVideos(movie_id)) {
            is Result.Success -> {
                val videos = result.data.map {
                    it.toVideo()
                }
                Result.Success(videos)

            }
            is Result.Error -> result
        }
    }
}