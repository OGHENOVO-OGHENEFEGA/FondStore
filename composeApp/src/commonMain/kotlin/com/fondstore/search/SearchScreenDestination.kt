package com.fondstore.search

import com.fondstore.product.domain.models.Product

sealed interface SearchScreenDestination {
    data object AuthScreen : SearchScreenDestination
    data class ProductScreen(val product: Product): SearchScreenDestination
}