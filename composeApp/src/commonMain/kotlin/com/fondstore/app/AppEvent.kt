package com.fondstore.app

import com.fondstore.product.domain.models.Product

sealed interface AppEvent {
    data object GetProducts : AppEvent
    data class ToggleProductFavouriteState(val product: Product) : AppEvent
    data object ClearDestination : AppEvent
}