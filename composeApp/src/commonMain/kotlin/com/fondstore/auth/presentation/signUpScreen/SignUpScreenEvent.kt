package com.fondstore.auth.presentation.signUpScreen

sealed interface SignUpScreenEvent {
    data class SetUsername(val username: String): SignUpScreenEvent
    data class SetEmail(val email: String): SignUpScreenEvent
    data class SetPassword(val password: String): SignUpScreenEvent
    data class SetConfirmPassword(val password: String): SignUpScreenEvent
    data object SignUp: SignUpScreenEvent
    data class Navigate(val destination: SignUpScreenDestination): SignUpScreenEvent
}