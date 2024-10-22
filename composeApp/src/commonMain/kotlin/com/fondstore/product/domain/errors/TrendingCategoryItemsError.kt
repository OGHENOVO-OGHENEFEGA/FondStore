package com.fondstore.product.domain.errors

data class TrendingCategoryItemsError(
    val error: String = "",
    val exception: Exception? = null
)
