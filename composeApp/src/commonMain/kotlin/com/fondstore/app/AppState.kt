package com.fondstore.app

import com.fondstore.auth.domain.models.AuthTokens
import com.fondstore.error.Result
import com.fondstore.favourites.domain.models.FavouritesState
import com.fondstore.product.domain.models.ProductsState
import com.fondstore.profile.domain.errors.ProfileError
import com.fondstore.profile.domain.models.Profile

data class AppState(
    val authTokens: AuthTokens? = null,
    val productsState: ProductsState = ProductsState(),
    val favouritesState: FavouritesState = FavouritesState(),
    val profileResult: Result<Profile?, ProfileError>? = null,
    val destination: AppDestination? = null,
)
