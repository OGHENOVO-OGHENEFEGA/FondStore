package com.fondstore.auth.presentation.signUpScreen

data class SignUpScreenState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isSigningUp: Boolean = false,
    val destination: SignUpScreenDestination? = null
)
