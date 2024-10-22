package com.fondstore.auth.domain.models

data class AuthTokens(
    val access: String = "",
    val refresh: String = "",
    val state: AuthTokensState = if (access.isBlank()) AuthTokensState.UNAVAILABLE else AuthTokensState.AVAILABLE
)
