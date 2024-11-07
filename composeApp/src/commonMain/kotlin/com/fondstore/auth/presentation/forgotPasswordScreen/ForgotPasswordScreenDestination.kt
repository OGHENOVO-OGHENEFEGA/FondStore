package com.fondstore.auth.presentation.forgotPasswordScreen

sealed interface ForgotPasswordScreenDestination {
    data object SignInScreen : ForgotPasswordScreenDestination
}