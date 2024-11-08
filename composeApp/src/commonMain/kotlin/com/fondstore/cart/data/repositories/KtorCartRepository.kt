package com.fondstore.cart.data.repositories

import com.fondstore.cart.data.mappers.toCart
import com.fondstore.cart.data.mappers.toError
import com.fondstore.cart.data.remote.requests.CartUpdateRequest
import com.fondstore.cart.data.remote.responses.CartResponse
import com.fondstore.cart.data.utils.CartDataUtil
import com.fondstore.cart.domain.errors.CartError
import com.fondstore.cart.domain.models.Cart
import com.fondstore.cart.domain.repositories.CartRepository
import com.fondstore.error.Result
import com.fondstore.ktor.authHeader
import com.fondstore.ktor.safeDelete
import com.fondstore.ktor.safeGet
import com.fondstore.ktor.safePost
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.setBody
import io.ktor.http.isSuccess

class KtorCartRepository(private val client: HttpClient) : CartRepository {
    override suspend fun getCart(token: String?): Result<Cart?, CartError> {
        return client.safeGet(
            urlString = CartDataUtil.GET_CART_URL,
            tag = CartDataUtil.GET_CART_TAG,
            requestBlock = {
                authHeader(token = token)
            },
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    Result.Success(
                        response.body<List<CartResponse.Success>>().firstOrNull()?.toCart()
                    )
                } else {
                    Result.Error(response.body<CartResponse.Error>().toError())
                }
            }
        )
    }

    override suspend fun updateCartItem(
        productId: String,
        quantity: Int,
        sizeId: Int?,
        cartId: String,
        token: String?,
    ): Result<Unit, CartError> {
        return client.safePost(
            urlString = CartDataUtil.getUpdateCartItemUrl(cartId = cartId),
            tag = CartDataUtil.UPDATE_CART_ITEM_TAG,
            requestBlock = {
                authHeader(token = token)

                setBody(
                    CartUpdateRequest(
                        itemId = productId,
                        quantity = quantity,
                        sizeId = sizeId
                    )
                )
            },
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    Result.Success(Unit)
                } else {
                    Result.Error(response.body<CartResponse.Error>().toError())
                }
            }
        )
    }

    override suspend fun removeCartItem(
        itemId: Int,
        cartId: String,
        token: String?,
    ): Result<Unit, CartError> {
        return client.safeDelete(
            urlString = CartDataUtil.getRemoveCartItemUrl(itemId = itemId, cartId = cartId),
            tag = CartDataUtil.REMOVE_CART_ITEM_TAG,
            requestBlock = {
                authHeader(token = token)
            },
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    Result.Success(Unit)
                } else {
                    Result.Error(response.body<CartResponse.Error>().toError())
                }
            }
        )
    }
}
