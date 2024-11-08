package com.fondstore.favourites.data.remote.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavouriteErrorResponse(
    @SerialName("detail")
    val detail: String = "",
    @SerialName("error")
    val error: String = ""
)
