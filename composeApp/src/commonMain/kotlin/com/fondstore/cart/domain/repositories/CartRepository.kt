package com.fondstore.cart.domain.repositories

import com.fondstore.cart.domain.errors.CartError
import com.fondstore.cart.domain.models.Cart
import com.fondstore.error.Result

interface CartRepository {
    suspend fun getCart(token: String?): Result<Cart?, CartError>

    suspend fun updateCartItem(
        productId: String,
        quantity: Int,
        sizeId: Int?,
        cartId: String,
        token: String?,
    ): Result<Unit, CartError>

    suspend fun removeCartItem(itemId: Int, cartId: String, token: String?): Result<Unit, CartError>
}