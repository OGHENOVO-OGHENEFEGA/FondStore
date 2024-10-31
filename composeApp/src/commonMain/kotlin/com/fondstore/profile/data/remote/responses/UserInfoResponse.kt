package com.fondstore.profile.data.remote.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface UserInfoResponse {
    @Serializable
    data class Success(
        @SerialName("email")
        val email: String = "",
        @SerialName("id")
        val id: Int = 0,
        @SerialName("username")
        val username: String = ""
    ) : UserInfoResponse

    @Serializable
    data class Error(
        @SerialName("detail")
        val detail: String = "",
        @SerialName("error")
        val error: String = "",
    ) : UserInfoResponse
}