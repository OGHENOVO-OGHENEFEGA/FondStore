package com.fondstore.auth.presentation.forgotPasswordScreen

sealed interface ForgotPasswordScreenEvent {
    data class SetEmail(val email: String) : ForgotPasswordScreenEvent
    data object ResetPassword : ForgotPasswordScreenEvent
    data class Navigate(val destination: ForgotPasswordScreenDestination) : ForgotPasswordScreenEvent
}