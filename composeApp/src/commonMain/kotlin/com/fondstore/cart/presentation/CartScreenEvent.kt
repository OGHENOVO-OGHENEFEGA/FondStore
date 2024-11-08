package com.fondstore.cart.presentation

import com.fondstore.cart.domain.models.CartItemSize
import com.fondstore.product.domain.models.Product

sealed interface CartScreenEvent {
    data class RemoveProduct(val product: Product, val sizeId: CartItemSize?): CartScreenEvent
    data class Navigate(val destination: CartScreenDestination) : CartScreenEvent
    data object ClearDestination : CartScreenEvent
}