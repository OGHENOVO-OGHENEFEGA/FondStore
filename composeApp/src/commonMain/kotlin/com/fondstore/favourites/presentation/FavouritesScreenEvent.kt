package com.fondstore.favourites.presentation

import com.fondstore.product.domain.models.Product

sealed interface FavouritesScreenEvent {
    data class RemoveProduct(val product: Product): FavouritesScreenEvent
    data class Navigate(val destination: FavouritesScreenDestination) : FavouritesScreenEvent
    data object ClearDestination : FavouritesScreenEvent
}