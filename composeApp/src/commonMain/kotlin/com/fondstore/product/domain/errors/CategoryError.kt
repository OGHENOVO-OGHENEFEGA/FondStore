package com.fondstore.product.domain.errors

data class CategoryError(
    val error: String = "",
    val exception: Exception? = null
)
