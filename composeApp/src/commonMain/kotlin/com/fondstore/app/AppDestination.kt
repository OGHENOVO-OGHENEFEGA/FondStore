package com.fondstore.app

sealed interface AppDestination {
    data object AuthScreen : AppDestination
}