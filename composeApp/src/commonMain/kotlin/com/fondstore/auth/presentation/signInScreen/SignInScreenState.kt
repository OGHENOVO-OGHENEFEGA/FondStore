package com.fondstore.auth.presentation.signInScreen

import com.fondstore.auth.domain.errors.SignInError
import com.fondstore.auth.domain.models.SignInInfo
import com.fondstore.error.Result

data class SignInScreenState(
    val email: String = "",
    val password: String = "",
    val isSigningIn: Boolean = false,
    val result: Result<SignInInfo, SignInError>? = null,
    val destination: SignInScreenDestination? = null,
    val isActive: Boolean = true,
)
