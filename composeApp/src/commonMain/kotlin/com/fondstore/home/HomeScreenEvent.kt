package com.fondstore.home

import com.fondstore.product.domain.models.Product

sealed interface HomeScreenEvent {
    data class ToggleProductFavouriteState(val product: Product) : HomeScreenEvent
    data class Navigate(val destination: HomeScreenDestination) : HomeScreenEvent
    data object ClearDestination : HomeScreenEvent
}