package com.fondstore.cart.data.remote.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartItemSizeResponse(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("section")
    val section: Int = 0,
    @SerialName("size")
    val size: String = ""
)