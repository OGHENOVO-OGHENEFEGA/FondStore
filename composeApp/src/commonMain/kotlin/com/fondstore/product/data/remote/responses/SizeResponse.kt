package com.fondstore.product.data.remote.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SizeResponse(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String = "",
    @SerialName("quantity")
    val quantity: Int = 0
)