package com.fondstore.cart.data.mappers

import com.fondstore.cart.data.remote.responses.CartItemResponse
import com.fondstore.cart.data.remote.responses.CartItemSizeResponse
import com.fondstore.cart.data.remote.responses.CartResponse
import com.fondstore.cart.domain.errors.CartError
import com.fondstore.cart.domain.models.Cart
import com.fondstore.cart.domain.models.CartItem
import com.fondstore.cart.domain.models.CartItemSize
import com.fondstore.product.data.mappers.toProduct

fun CartResponse.Success.toCart(): Cart {
    return Cart(
        cartItems = cartItems.map(CartItemResponse::toCartItem),
        id = id,
        priceGrandTotal = priceGrandTotal,
        totalQuantity = totalQuantity
    )
}

fun CartResponse.Error.toError(): CartError {
    return CartError(error = error.ifBlank { detail })
}

private fun CartItemResponse.toCartItem(): CartItem {
    return CartItem(
        cart = cart,
        id = id,
        item = item.toProduct(),
        quantity = quantity,
        size = size?.toSize()
    )
}

private fun CartItemSizeResponse.toSize(): CartItemSize {
    return CartItemSize(id = id, section = section, size = size)
}