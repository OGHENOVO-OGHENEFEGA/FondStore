package com.fondstore.cart.presentation

import com.fondstore.product.domain.models.Product

sealed interface CartScreenDestination {
    data object HomeScreen : CartScreenDestination
    data class ProductScreen(val product: Product) : CartScreenDestination
    data class AddressScreen(val cartId: String) : CartScreenDestination
}