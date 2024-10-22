package com.fondstore.product.domain.errors

data class ProductError(
    val error: String = "",
    val exception: Exception? = null
)
