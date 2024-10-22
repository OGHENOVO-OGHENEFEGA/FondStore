package com.fondstore.product.data.remote.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewArrivalResponse(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("item")
    val item: ProductResponse.Success = ProductResponse.Success()
)