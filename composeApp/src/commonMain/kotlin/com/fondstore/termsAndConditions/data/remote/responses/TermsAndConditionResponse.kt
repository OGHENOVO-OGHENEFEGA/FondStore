package com.fondstore.termsAndConditions.data.remote.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface TermsAndConditionResponse {
    @Serializable
    data class Success(
        @SerialName("terms_and_condition")
        val termsAndCondition: String = ""
    ) : TermsAndConditionResponse

    @Serializable
    data class Error(
        @SerialName("detail")
        val detail: String = "",
        @SerialName("error")
        val error: String = "",
    ) : TermsAndConditionResponse
}