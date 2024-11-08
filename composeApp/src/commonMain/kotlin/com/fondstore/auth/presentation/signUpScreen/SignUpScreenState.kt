package com.fondstore.auth.presentation.signUpScreen

import com.fondstore.auth.domain.errors.SignUpError
import com.fondstore.auth.domain.models.SignUpInfo
import com.fondstore.error.Result

data class SignUpScreenState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isSigningUp: Boolean = false,
    val result: Result<SignUpInfo, SignUpError>? = null,
    val destination: SignUpScreenDestination? = null
)
