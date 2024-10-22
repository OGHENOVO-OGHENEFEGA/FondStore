package com.fondstore.notification.presentation

import com.fondstore.error.domain.models.Result
import com.fondstore.notification.domain.errors.NotificationError
import com.fondstore.notification.domain.models.Notification

data class NotificationsScreenState(
    val isRefreshingScreen: Boolean = false,
    val isGettingNotifications: Boolean = false,
    val result: Result<List<Notification>, NotificationError>? = null,
    val isActive: Boolean = true
)
