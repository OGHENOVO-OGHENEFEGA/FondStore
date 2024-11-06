package com.fondstore.contactUs.data.remote.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface HelpCentreResponse {
    @Serializable
    data class Success(
        @SerialName("address")
        val address: String = "",
        @SerialName("email")
        val email: String = "",
        @SerialName("phone_number")
        val phoneNumber: String = ""
    ) : HelpCentreResponse

    @Serializable
    data class Error(
        @SerialName("detail")
        val detail: String = "",
        @SerialName("error")
        val error: String = "",
    ) : HelpCentreResponse
}