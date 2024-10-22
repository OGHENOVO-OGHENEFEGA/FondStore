package com.fondstore.product.domain.errors

data class SectionItemsError(
    val error: String = "",
    val exception: Exception? = null
)
