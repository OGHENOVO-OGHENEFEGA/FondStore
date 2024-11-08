package com.fondstore.favourites.domain.repositories

import com.fondstore.error.Result
import com.fondstore.favourites.domain.errors.FavouriteError
import com.fondstore.product.domain.errors.ProductError
import com.fondstore.product.domain.models.Product

interface FavouritesRepository {
    suspend fun getFavourites(token: String?): Result<List<Product>, ProductError>
    suspend fun addToFavourites(productId: String, token: String?): Result<Unit, FavouriteError>
    suspend fun removeFromFavourites(favouriteId: String, token: String?): Result<Unit, FavouriteError>
}