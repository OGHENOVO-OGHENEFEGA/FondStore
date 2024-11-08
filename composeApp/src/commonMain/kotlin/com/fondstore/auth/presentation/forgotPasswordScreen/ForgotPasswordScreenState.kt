package com.fondstore.auth.presentation.forgotPasswordScreen

import com.fondstore.auth.domain.errors.ResetPasswordError
import com.fondstore.auth.domain.models.ResetPasswordInfo
import com.fondstore.error.Result

data class ForgotPasswordScreenState(
    val email: String = "",
    val isResettingPassword: Boolean = false,
    val result: Result<ResetPasswordInfo, ResetPasswordError>? = null,
    val destination: ForgotPasswordScreenDestination? = null,
)
