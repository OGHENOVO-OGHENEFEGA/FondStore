package com.fondstore.favourites.data.repositories

import com.fondstore.error.Result
import com.fondstore.favourites.data.mappers.toError
import com.fondstore.favourites.data.remote.requests.AddToFavouritesRequest
import com.fondstore.favourites.data.remote.responses.FavouriteErrorResponse
import com.fondstore.favourites.data.utils.FavouritesDataUtil
import com.fondstore.favourites.domain.errors.FavouriteError
import com.fondstore.favourites.domain.repositories.FavouritesRepository
import com.fondstore.ktor.authHeader
import com.fondstore.ktor.safeDelete
import com.fondstore.ktor.safeGet
import com.fondstore.ktor.safePost
import com.fondstore.product.data.mappers.toError
import com.fondstore.product.data.mappers.toProduct
import com.fondstore.product.data.remote.responses.ProductResponse
import com.fondstore.product.domain.errors.ProductError
import com.fondstore.product.domain.models.Product
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.setBody
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess

class KtorFavouritesRepository(private val client: HttpClient) : FavouritesRepository {
    override suspend fun getFavourites(token: String?): Result<List<Product>, ProductError> {
        return client.safeGet(
            urlString = FavouritesDataUtil.GET_FAVOURITES_URL,
            tag = FavouritesDataUtil.GET_FAVOURITES_TAG,
            requestBlock = {
                authHeader(token = token)
            },
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    Result.Success(
                        response.body<List<ProductResponse.Success>>()
                            .map(ProductResponse.Success::toProduct)
                    )
                } else {
                    Result.Error(response.body<ProductResponse.Error>().toError())
                }
            }
        )
    }

    override suspend fun addToFavourites(
        productId: String,
        token: String?,
    ): Result<Unit, FavouriteError> {
        return client.safePost(
            urlString = FavouritesDataUtil.ADD_TO_FAVOURITES_URL,
            tag = FavouritesDataUtil.ADD_TO_FAVOURITES_TAG,
            requestBlock = {
                authHeader(token = token)
                setBody(AddToFavouritesRequest(itemId = productId))
            },
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    Result.Success(Unit)
                } else {
                    Result.Error(response.body<FavouriteErrorResponse>().toError())
                }
            }
        )
    }

    override suspend fun removeFromFavourites(
        favouriteId: String,
        token: String?,
    ): Result<Unit, FavouriteError> {
        return client.safeDelete(
            urlString = FavouritesDataUtil.getRemoveFromFavouritesUrl(id = favouriteId),
            tag = FavouritesDataUtil.REMOVE_FROM_FAVOURITES_TAG,
            requestBlock = {
                authHeader(token = token)
            },
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    Result.Success(Unit)
                } else {
                    Result.Error(response.body<FavouriteErrorResponse>().toError())
                }
            }
        )
    }
}