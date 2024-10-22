package com.fondstore.notification.domain.errors

data class NotificationError(
    val error: String = "",
    val exception: Exception? = null
)