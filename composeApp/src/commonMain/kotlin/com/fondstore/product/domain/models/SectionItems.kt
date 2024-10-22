package com.fondstore.product.domain.models

data class SectionItems(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Product>
)