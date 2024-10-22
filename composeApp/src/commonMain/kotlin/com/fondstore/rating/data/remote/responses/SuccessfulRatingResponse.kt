package com.fondstore.rating.data.remote.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SuccessfulRatingResponse(
    @SerialName("description")
    val description: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("rating")
    val rating: Int = 0
)