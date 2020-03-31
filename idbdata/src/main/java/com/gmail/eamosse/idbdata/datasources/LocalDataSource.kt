package com.gmail.eamosse.idbdata.datasources

import com.gmail.eamosse.idbdata.local.daos.MovieDao
import com.gmail.eamosse.idbdata.local.daos.TokenDao
import com.gmail.eamosse.idbdata.local.entities.MovieEntity
import com.gmail.eamosse.idbdata.local.entities.ProfileEntity
import com.gmail.eamosse.idbdata.local.entities.TokenEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

internal class LocalDataSource : KoinComponent {
    private val tokenDao: TokenDao by inject()
    private val movieDao: MovieDao by inject()

    suspend fun getToken() = withContext(Dispatchers.IO) {
        tokenDao.retrieve()
    }

    suspend fun saveToken(entity: TokenEntity) = withContext(Dispatchers.IO) {
        tokenDao.insert(entity)
    }

    suspend fun registerMovie(entity: MovieEntity) = withContext(Dispatchers.IO) {
        movieDao.insert(entity)
    }

    suspend fun likeMovie(id: Int, value: Boolean) = withContext(Dispatchers.IO) {
        movieDao.setLike(id, value)
    }

    suspend fun favMovie(id: Int, value: Boolean) = withContext(Dispatchers.IO) {
        movieDao.setFavorite(id, value)
    }

    suspend fun getLiked() = withContext(Dispatchers.IO) {
        movieDao.getLiked()
    }

    suspend fun getFavorites() = withContext(Dispatchers.IO) {
        movieDao.getFavorites()
    }

    suspend fun isLiked(movie_id: Int) = withContext(Dispatchers.IO) {
        movieDao.isLiked(movie_id)
    }

    suspend fun isFavorite(movie_id: Int) = withContext(Dispatchers.IO) {
        movieDao.isFavorite(movie_id)
    }
}