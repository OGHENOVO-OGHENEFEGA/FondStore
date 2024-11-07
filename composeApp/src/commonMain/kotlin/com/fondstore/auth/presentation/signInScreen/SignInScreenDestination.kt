package com.fondstore.auth.presentation.signInScreen

sealed interface SignInScreenDestination {
    data object SignUpScreen : SignInScreenDestination
    data object ForgotPasswordScreen : SignInScreenDestination
}