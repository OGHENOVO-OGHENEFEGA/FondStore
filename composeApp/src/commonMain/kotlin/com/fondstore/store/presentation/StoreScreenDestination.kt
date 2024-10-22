package com.fondstore.store.presentation

sealed interface StoreScreenDestination {
    data object SearchScreen : StoreScreenDestination
    data object NotificationsScreen : StoreScreenDestination
}