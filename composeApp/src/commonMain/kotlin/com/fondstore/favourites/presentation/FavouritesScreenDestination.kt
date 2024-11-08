package com.fondstore.favourites.presentation

import com.fondstore.product.domain.models.Product

sealed interface FavouritesScreenDestination {
    data object HomeScreen : FavouritesScreenDestination
    data class ProductScreen(val product: Product) : FavouritesScreenDestination
}