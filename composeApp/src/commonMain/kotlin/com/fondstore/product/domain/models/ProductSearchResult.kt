package com.fondstore.product.domain.models

data class ProductSearchResult(
    val query: String,
    val results: List<Product>
)