package com.fondstore.category.trendingCategoryScreen

import com.fondstore.product.domain.models.Product

sealed interface TrendingCategoryScreenEvent {
    data object GetNextItems : TrendingCategoryScreenEvent
    data class ToggleProductFavouriteState(val product: Product) : TrendingCategoryScreenEvent
    data class Navigate(val destination: TrendingCategoryScreenDestination) : TrendingCategoryScreenEvent
    data object ClearDestination : TrendingCategoryScreenEvent
}