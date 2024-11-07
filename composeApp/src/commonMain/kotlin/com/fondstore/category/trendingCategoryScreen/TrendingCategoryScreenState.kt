package com.fondstore.category.trendingCategoryScreen

import com.fondstore.error.Result
import com.fondstore.product.domain.errors.TrendingCategoryItemsError
import com.fondstore.product.domain.models.Category
import com.fondstore.product.domain.models.TrendingCategoryItems

data class TrendingCategoryScreenState(
    val category: Category,
    val isGettingItems: Boolean = false,
    val isGettingNextItems: Boolean = false,
    val result: Result<TrendingCategoryItems, TrendingCategoryItemsError>?  = null,
    val destination: TrendingCategoryScreenDestination? = null
)
