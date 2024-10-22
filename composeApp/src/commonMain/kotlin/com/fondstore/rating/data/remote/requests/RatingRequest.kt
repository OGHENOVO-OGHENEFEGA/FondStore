package com.fondstore.rating.data.remote.requests


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RatingRequest(
    @SerialName("description")
    val description: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("rating")
    val rating: Int = 0
)