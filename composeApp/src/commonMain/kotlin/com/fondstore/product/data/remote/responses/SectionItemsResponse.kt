package com.fondstore.product.data.remote.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface SectionItemsResponse {
    @Serializable
    data class Success(
        @SerialName("count")
        val count: Int = 0,
        @SerialName("next")
        val next: String? = null,
        @SerialName("previous")
        val previous: String? = null,
        @SerialName("results")
        val results: List<ProductResponse.Success> = listOf()
    ) : SectionItemsResponse

    @Serializable
    data class Error(
        @SerialName("detail")
        val detail: String = "",
        @SerialName("error")
        val error: String = ""
    ) : SectionItemsResponse
}