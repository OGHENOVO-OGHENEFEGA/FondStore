package com.fondstore.auth.domain.errors

data class SignUpError(
    val username: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)
