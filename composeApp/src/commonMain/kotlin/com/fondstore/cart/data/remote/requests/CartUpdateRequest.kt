package com.fondstore.cart.data.remote.requests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartUpdateRequest(
    @SerialName("item_id")
    val itemId: String = "",
    @SerialName("quantity")
    val quantity: Int = 0,
    @SerialName("size")
    val sizeId: Int? = null
)