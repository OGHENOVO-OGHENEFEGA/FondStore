package com.fondstore.product.data.remote.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface CategoryResponse {
    @Serializable
    data class Success(
        @SerialName("description")
        val description: String = "",
        @SerialName("id")
        val id: Int = 0,
        @SerialName("image")
        val image: String = "",
        @SerialName("name")
        val name: String = ""
    ) : CategoryResponse

    @Serializable
    data class Error(
        @SerialName("detail")
        val detail: String = "",
        @SerialName("error")
        val error: String = ""
    ) : CategoryResponse
}