package com.fondstore.profile.domain.models

data class Profile(
    val id: Int = -1,
    val image: String = "",
    val username: String = "",
    val email: String = "",
    val firstname: String = "",
    val lastname: String = "",
    val phoneNumber: String = "",
)