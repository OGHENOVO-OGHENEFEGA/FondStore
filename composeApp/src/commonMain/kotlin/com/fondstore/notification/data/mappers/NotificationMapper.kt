package com.fondstore.notification.data.mappers

import com.fondstore.notification.data.remote.responses.NotificationResponse
import com.fondstore.notification.domain.errors.NotificationError
import com.fondstore.notification.domain.models.Notification

fun NotificationResponse.Success.toNotification(): Notification {
    return Notification(attendedTo = attendedTo, notice = notice)
}

fun NotificationResponse.Error.toError(): NotificationError {
    return NotificationError(error = error.ifBlank { detail })
}