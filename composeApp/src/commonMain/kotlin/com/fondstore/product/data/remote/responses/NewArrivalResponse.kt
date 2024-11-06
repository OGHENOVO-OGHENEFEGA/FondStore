package com.fondstore.product.data.remote.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface NewArrivalResponse {
    @Serializable
    data class Success(
        @SerialName("id")
        val id: Int = 0,
        @SerialName("item")
        val item: ProductResponse.Success = ProductResponse.Success(),
    ) : NewArrivalResponse

    @Serializable
    data class Error(
        @SerialName("detail")
        val detail: String = "",
        @SerialName("error")
        val error: String = "",
    ) : NewArrivalResponse
}