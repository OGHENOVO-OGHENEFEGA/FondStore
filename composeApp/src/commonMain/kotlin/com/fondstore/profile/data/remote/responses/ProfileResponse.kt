package com.fondstore.profile.data.remote.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface ProfileResponse {
    @Serializable
    data class Success(
        @SerialName("id")
        val id: Int = 0,
        @SerialName("image")
        val image: String = "",
        @SerialName("first_name")
        val firstname: String = "",
        @SerialName("last_name")
        val lastname: String = "",
        @SerialName("phone_number")
        val phoneNumber: String = "",
        @SerialName("referral_code")
        val referralCode: String? = null
    ) : ProfileResponse

    @Serializable
    data class Error(
        @SerialName("detail")
        val detail: String = "",
        @SerialName("error")
        val error: String = "",
    ) : ProfileResponse
}