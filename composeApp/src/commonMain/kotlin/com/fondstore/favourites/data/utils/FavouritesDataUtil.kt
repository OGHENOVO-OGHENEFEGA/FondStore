package com.fondstore.favourites.data.utils

object FavouritesDataUtil {
    private const val FAVOURITES_URL = "api/favourites"

    const val GET_FAVOURITES_URL = FAVOURITES_URL
    const val GET_FAVOURITES_TAG = "GET FAVOURITES"

    const val ADD_TO_FAVOURITES_URL = "$FAVOURITES_URL/"
    const val ADD_TO_FAVOURITES_TAG = "ADD TO FAVOURITES"
    const val REMOVE_FROM_FAVOURITES_TAG = "REMOVE FROM FAVOURITES"

    fun getRemoveFromFavouritesUrl(id: String) = "$FAVOURITES_URL/$id/"
}