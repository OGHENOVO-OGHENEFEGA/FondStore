package com.fondstore.search

import com.fondstore.product.domain.models.Product

sealed interface SearchScreenEvent {
    data class SetSearchQuery(val query: String): SearchScreenEvent
    data object SearchProducts : SearchScreenEvent
    data class ToggleProductFavouriteState(val product: Product) : SearchScreenEvent
    data class Navigate(val destination: SearchScreenDestination) : SearchScreenEvent
    data object ClearDestination : SearchScreenEvent
    data object CloseScreen : SearchScreenEvent
}