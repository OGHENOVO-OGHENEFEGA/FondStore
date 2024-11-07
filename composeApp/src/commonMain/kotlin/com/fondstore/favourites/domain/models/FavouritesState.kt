package com.fondstore.favourites.domain.models

import com.fondstore.error.Result
import com.fondstore.product.domain.errors.ProductError
import com.fondstore.product.domain.models.Product

data class FavouritesState(
    val isGettingFavourites: Boolean = false,
    val requestLoadingList: List<Product> = listOf(),
    val result: Result<List<Product>, ProductError>? = null,
)
