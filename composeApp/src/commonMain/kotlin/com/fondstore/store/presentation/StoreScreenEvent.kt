package com.fondstore.store.presentation

sealed interface StoreScreenEvent {
    data class Navigate(val destination: StoreScreenDestination) : StoreScreenEvent
    data object ClearDestination : StoreScreenEvent
}