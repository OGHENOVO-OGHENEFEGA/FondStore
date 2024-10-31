package com.fondstore.faqs.data.remote.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface FaqResponse {
    @Serializable
    data class Success(
        @SerialName("answer")
        val answer: String = "",
        @SerialName("question")
        val question: String = ""
    ) : FaqResponse

    @Serializable
    data class Error(
        @SerialName("detail")
        val detail: String = "",
        @SerialName("error")
        val error: String = "",
    ) : FaqResponse
}