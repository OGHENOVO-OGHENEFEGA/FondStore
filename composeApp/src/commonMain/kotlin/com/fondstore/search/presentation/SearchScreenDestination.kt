package com.fondstore.search.presentation

sealed interface SearchScreenDestination {
    data object AuthScreen : SearchScreenDestination
    data class ProductScreen(val productId: String): SearchScreenDestination
}