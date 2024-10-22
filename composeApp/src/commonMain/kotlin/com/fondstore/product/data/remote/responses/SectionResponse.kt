package com.fondstore.product.data.remote.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface SectionResponse {
    @Serializable
    data class Success(
        @SerialName("category")
        val category: Int = 0,
        @SerialName("id")
        val id: Int = 0,
        @SerialName("name")
        val name: String = "",
        @SerialName("sub_category")
        val subCategory: Int = 0
    ) : SectionResponse

    @Serializable
    data class Error(
        @SerialName("detail")
        val detail: String = "",
        @SerialName("error")
        val error: String = ""
    ) : SectionResponse
}