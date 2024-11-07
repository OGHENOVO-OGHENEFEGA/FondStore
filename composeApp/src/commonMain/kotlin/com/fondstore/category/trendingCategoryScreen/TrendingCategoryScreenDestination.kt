package com.fondstore.category.trendingCategoryScreen

import com.fondstore.product.domain.models.Product

sealed interface TrendingCategoryScreenDestination {
    data class ProductScreen(val product: Product) : TrendingCategoryScreenDestination
}