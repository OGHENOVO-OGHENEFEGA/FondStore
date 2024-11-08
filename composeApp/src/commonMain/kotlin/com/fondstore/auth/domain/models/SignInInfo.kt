package com.fondstore.auth.domain.models

data class SignInInfo(
    val access: String,
    val refresh: String,
)