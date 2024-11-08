package com.fondstore.cart.domain.models

data class Cart(
    val cartItems: List<CartItem>,
    val id: String,
    val priceGrandTotal: Double,
    val totalQuantity: Int
)