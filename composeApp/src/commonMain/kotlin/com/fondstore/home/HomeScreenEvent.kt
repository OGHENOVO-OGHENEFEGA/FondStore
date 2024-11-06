package com.fondstore.home

sealed interface HomeScreenEvent {
    data object RefreshScreen : HomeScreenEvent
    data class ToggleProductFavouriteState(val productId: String) : HomeScreenEvent
    data class Navigate(val destination: HomeScreenDestination) : HomeScreenEvent
    data object ClearDestination : HomeScreenEvent
}