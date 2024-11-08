package com.fondstore.favourites.data.remote.requests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddToFavouritesRequest(
    @SerialName("item_id")
    val itemId: String = ""
)