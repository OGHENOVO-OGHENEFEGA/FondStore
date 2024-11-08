package com.fondstore.auth.data.remote.responses

import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName

@Serializable
sealed interface SignInResponse {
    @Serializable
    data class Error(
        @SerialName("detail")
        val detail: String = "",
        @SerialName("email")
        val email: List<String> = listOf(),
        @SerialName("error")
        val error: String = "",
        @SerialName("password")
        val password: List<String> = listOf()
    )

    @Serializable
    data class Success(
        @SerialName("access")
        val access: String = "",
        @SerialName("refresh")
        val refresh: String = ""
    ): SignInResponse
}