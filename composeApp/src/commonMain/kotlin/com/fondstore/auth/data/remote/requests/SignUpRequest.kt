package com.fondstore.auth.data.remote.requests


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest(
    @SerialName("email")
    val email: String = "",
    @SerialName("password")
    val password: String = "",
    @SerialName("re_password")
    val rePassword: String = "",
    @SerialName("username")
    val username: String = ""
)