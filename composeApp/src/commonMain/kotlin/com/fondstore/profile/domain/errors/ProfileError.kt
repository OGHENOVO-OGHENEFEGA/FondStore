package com.fondstore.profile.domain.errors

data class ProfileError(
    val error: String = "",
    val exception: Exception? = null
)
