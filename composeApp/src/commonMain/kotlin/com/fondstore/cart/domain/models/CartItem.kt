package com.fondstore.cart.domain.models

import com.fondstore.product.domain.models.Product

data class CartItem(
    val cart: String,
    val id: Int,
    val item: Product,
    val quantity: Int,
    val size: CartItemSize?
)