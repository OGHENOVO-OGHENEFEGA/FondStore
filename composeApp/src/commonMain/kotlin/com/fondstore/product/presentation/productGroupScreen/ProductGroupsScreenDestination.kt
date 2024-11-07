package com.fondstore.product.presentation.productGroupScreen

import com.fondstore.product.domain.models.Product

sealed interface ProductGroupsScreenDestination {
    data object AuthScreen : ProductGroupsScreenDestination
    data class ProductScreen(val product: Product) : ProductGroupsScreenDestination
}