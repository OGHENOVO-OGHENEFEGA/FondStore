package com.fondstore.notification.domain.repositories

import com.fondstore.error.Result
import com.fondstore.notification.domain.errors.NotificationError
import com.fondstore.notification.domain.models.Notification

interface NotificationRepository {
    suspend fun getNotifications(token: String?): Result<List<Notification>, NotificationError>
}