package com.fondstore.category.trendingCategoryScreen

import com.fondstore.product.domain.models.Product

sealed interface TrendingCategoryScreenDestination {
    data object AuthScreen: TrendingCategoryScreenDestination
    data class ProductScreen(val product: Product) : TrendingCategoryScreenDestination
}