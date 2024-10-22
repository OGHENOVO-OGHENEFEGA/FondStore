package com.fondstore.notification.domain.models

data class Notification(
    val attendedTo: Boolean = false,
    val notice: String = ""
)