package com.gmail.eamosse.idbdata.local.entities
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Modélise les tokens dans la base de données
 */
@Entity(
    tableName = "idb_user"
)
internal data class ProfileEntity(
    @PrimaryKey
    val movie_id: Int,
    val isLiked: Boolean,
    val isFavorite: Boolean
)