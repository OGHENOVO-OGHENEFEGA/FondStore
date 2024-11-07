package com.fondstore.auth.presentation.forgotPasswordScreen

data class ForgotPasswordScreenState(
    val email: String = "",
    val isResettingPassword: Boolean = false,
    val destination: ForgotPasswordScreenDestination? = null
)
