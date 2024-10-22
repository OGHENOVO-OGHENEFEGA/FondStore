package com.fondstore.search.presentation

sealed interface SearchScreenEvent {
    data class SetSearchQuery(val query: String): SearchScreenEvent
    data object SearchProducts : SearchScreenEvent
    data class ToggleProductFavouritesState(val productId: String) : SearchScreenEvent
    data class Navigate(val destination: SearchScreenDestination) : SearchScreenEvent
    data object ClearDestination : SearchScreenEvent
    data object CloseScreen : SearchScreenEvent
}