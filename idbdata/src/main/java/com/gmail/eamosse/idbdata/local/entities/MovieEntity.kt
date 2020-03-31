package com.gmail.eamosse.idbdata.local.entities
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Modélise les tokens dans la base de données
 */
@Entity(
    tableName = "movies"
)
internal data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val isLiked: Boolean,
    val isFavorite: Boolean
)