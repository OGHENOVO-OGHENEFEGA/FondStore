package com.fondstore.auth.data.remote.responses

import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName

@Serializable
sealed interface SignUpResponse {
    @Serializable
    data class Error(
        @SerialName("detail")
        val detail: String = "",
        @SerialName("email")
        val email: List<String> = listOf(),
        @SerialName("error")
        val error: String = "",
        @SerialName("password")
        val password: List<String> = listOf(),
        @SerialName("re_password")
        val rePassword: List<String> = listOf(),
        @SerialName("username")
        val username: List<String> = listOf()
    ) : SignUpResponse

    @Serializable
    data class Success(
        @SerialName("email")
        val email: String = "",
        @SerialName("id")
        val id: Int = 0,
        @SerialName("username")
        val username: String = ""
    ) : SignUpResponse
}