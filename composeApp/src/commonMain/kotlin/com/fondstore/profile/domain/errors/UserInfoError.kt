package com.fondstore.profile.domain.errors

data class UserInfoError(
    val error: String = "",
    val exception: Exception? = null
)
