package com.fondstore.auth.presentation.signUpScreen

sealed interface SignUpScreenDestination {
    data object SignInScreen : SignUpScreenDestination
}