package com.fondstore.favourites.data.mappers

import com.fondstore.favourites.data.remote.responses.FavouriteErrorResponse
import com.fondstore.favourites.domain.errors.FavouriteError

fun FavouriteErrorResponse.toError(): FavouriteError {
    return FavouriteError(error = error.ifBlank { detail })
}