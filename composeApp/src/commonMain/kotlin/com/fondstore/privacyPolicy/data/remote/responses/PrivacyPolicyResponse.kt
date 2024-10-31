package com.fondstore.privacyPolicy.data.remote.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface PrivacyPolicyResponse {
    @Serializable
    data class Success(
        @SerialName("privacy_policy")
        val privacyPolicy: String = ""
    ) : PrivacyPolicyResponse

    @Serializable
    data class Error(
        @SerialName("detail")
        val detail: String = "",
        @SerialName("error")
        val error: String = "",
    ) : PrivacyPolicyResponse
}