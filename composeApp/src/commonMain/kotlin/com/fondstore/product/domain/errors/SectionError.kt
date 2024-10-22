package com.fondstore.product.domain.errors

data class SectionError(
    val error: String = "",
    val exception: Exception? = null
)
