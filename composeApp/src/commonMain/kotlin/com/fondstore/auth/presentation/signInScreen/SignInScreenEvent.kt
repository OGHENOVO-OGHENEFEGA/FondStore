package com.fondstore.auth.presentation.signInScreen

sealed interface SignInScreenEvent {
    data class SetEmail(val email: String) : SignInScreenEvent
    data class SetPassword(val password: String) : SignInScreenEvent
    data object SignIn : SignInScreenEvent
    data object CancelSignIn : SignInScreenEvent
    data class Navigate(val destination: SignInScreenDestination) : SignInScreenEvent
    data object ClearDestination : SignInScreenEvent
    data object ClearScreen : SignInScreenEvent
    data object CloseScreen : SignInScreenEvent
}