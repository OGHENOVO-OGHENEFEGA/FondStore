package com.fondstore.auth.data.remote.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResetPasswordErrorResponse(
    @SerialName("detail")
    val detail: String = "",
    @SerialName("email")
    val email: List<String> = listOf(),
    @SerialName("error")
    val error: String = ""
)
