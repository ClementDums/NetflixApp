package com.gmail.eamosse.idbdata.local.daos

import androidx.room.*
import com.gmail.eamosse.idbdata.local.entities.MovieEntity

@Dao
internal interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: MovieEntity)

    @Query("UPDATE idb_user SET isLiked = :liked WHERE movie_id = :id")
    fun setLike(id: Int, liked: Boolean)

    @Query("UPDATE idb_user SET isFavorite = :favorite WHERE movie_id = :id")
    fun setFavorite(id: Int, favorite: Boolean)

    @Query("SELECT isLiked FROM idb_user WHERE movie_id = :id")
    fun isLiked(id: Int): Int

    @Query("SELECT isFavorite FROM idb_user WHERE movie_id = :id")
    fun isFavorite(id: Int): Int

    @Query("SELECT * from idb_movies m INNER JOIN idb_user u ON m.id = u.movie_id WHERE isLiked = 1")
    fun getLiked(): List<MovieEntity>

    @Query("SELECT * from idb_movies m INNER JOIN idb_user u ON m.id = u.movie_id WHERE isFavorite = 1")
    fun getFavorites(): List<MovieEntity>

    @Query("DELETE FROM idb_movies")
    fun delete()
}