package com.fondstore.store

sealed interface StoreScreenDestination {
    data object SearchScreen : StoreScreenDestination
    data object NotificationsScreen : StoreScreenDestination
}