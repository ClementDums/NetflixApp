package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Category

internal data class CategoryResponse(val genres: List<Genre>) {
    data class Genre(
        val id: Int,
        val name: String
    ) {
        fun toCategory(): Category = Category(id, name)
    }
}

