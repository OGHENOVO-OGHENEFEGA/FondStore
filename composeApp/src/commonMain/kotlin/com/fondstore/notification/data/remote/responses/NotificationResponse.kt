package com.fondstore.notification.data.remote.responses


import com.fondstore.product.data.remote.responses.CategoryResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface NotificationResponse {
    @Serializable
    data class Success(
        @SerialName("attended_to")
        val attendedTo: Boolean = false,
        @SerialName("notice")
        val notice: String = ""
    ) : NotificationResponse

    @Serializable
    data class Error(
        @SerialName("detail")
        val detail: String = "",
        @SerialName("error")
        val error: String = ""
    ) : NotificationResponse
}