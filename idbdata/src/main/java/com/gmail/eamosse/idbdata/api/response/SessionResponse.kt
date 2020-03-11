package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Session
import com.google.gson.annotations.SerializedName

internal data class SessionResponse(
    @SerializedName("success")
    val success: Boolean? = null,
    @SerializedName("session_id")
    val sessionId: String? = null)

internal fun SessionResponse.toSession() = Session(
    success = success!!,
    sessionId = sessionId!!
)