package com.fondstore.rating.data.remote.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RatingResponse(
    @SerialName("created_at")
    val createdAt: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("rating")
    val rating: Float = 0f,
    @SerialName("username")
    val username: String = ""
)