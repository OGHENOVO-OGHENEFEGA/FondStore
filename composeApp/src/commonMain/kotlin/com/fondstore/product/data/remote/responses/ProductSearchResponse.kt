package com.fondstore.product.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class ProductSearchResponse(
    @Serializable
    val query: String = "",
    @Serializable
    val results: List<ProductResponse.Success> = listOf()
)