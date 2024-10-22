package com.fondstore.product.domain.errors

data class SubcategoryError(
    val error: String = "",
    val exception: Exception? = null
)
