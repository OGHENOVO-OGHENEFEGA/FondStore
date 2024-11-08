package com.fondstore.auth.domain.errors

data class SignInError(
    val email: String,
    val password: String
)
