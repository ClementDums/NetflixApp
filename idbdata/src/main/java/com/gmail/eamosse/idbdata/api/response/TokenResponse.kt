package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Token
import com.gmail.eamosse.idbdata.local.entities.TokenEntity
import com.google.gson.annotations.SerializedName

/**
 * Classe modélisant la réponse du serveur lors de la demande d'un Token
 * Cette classe est uniquement utilisée pour récupérer la réponse du serveur
 * Les instances de cette classe ne sont jamais exposées aux autres composants
 */
internal data class TokenResponse(
    @SerializedName("expires_at")
    val expiresAt: String? = null,
    @SerializedName("request_token")
    val requestToken: String? = null,
    @SerializedName("success")
    val success: Boolean? = null
)

/**
 * Une classe d'extension utilisée pour convertir la réponse en objet exploitable
 * par les autres composants de l'application
 */
internal fun TokenResponse.toToken() = Token(
    expiresAt = expiresAt!!,
    requestToken = requestToken!!
)

/**
 * Une classe d'extension utilisée pour convertir la réponse en entité
 */
internal fun TokenResponse.toEntity() = TokenEntity(
    expiresAt = expiresAt!!,
    token = requestToken!!
)