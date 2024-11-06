package com.fondstore.profile.presentation

sealed interface ProfileScreenDestination {
    data object AuthScreen : ProfileScreenDestination
}