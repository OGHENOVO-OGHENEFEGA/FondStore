package com.fondstore.auth.presentation.signInScreen

data class SignInScreenState(
    val email: String = "",
    val password: String = "",
    val isSigningIn: Boolean = false,
    val destination: SignInScreenDestination? = null,
    val isActive: Boolean = true,
)
