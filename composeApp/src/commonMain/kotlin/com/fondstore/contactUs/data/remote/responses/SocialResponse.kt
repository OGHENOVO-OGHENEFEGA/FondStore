package com.fondstore.contactUs.data.remote.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface SocialResponse {
    @Serializable
    data class Success(
        @SerialName("icon")
        val icon: String = "",
        @SerialName("name")
        val name: String = "",
        @SerialName("url")
        val url: String = "",
    ) : SocialResponse

    @Serializable
    data class Error(
        @SerialName("detail")
        val detail: String = "",
        @SerialName("error")
        val error: String = "",
    ) : SocialResponse
}