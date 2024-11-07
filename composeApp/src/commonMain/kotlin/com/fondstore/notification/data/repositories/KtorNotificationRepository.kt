package com.fondstore.notification.data.repositories

import com.fondstore.error.Result
import com.fondstore.ktor.authHeader
import com.fondstore.ktor.safeGet
import com.fondstore.notification.data.mappers.toError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import com.fondstore.notification.data.mappers.toNotification
import com.fondstore.notification.data.remote.responses.NotificationResponse
import com.fondstore.notification.domain.models.Notification
import com.fondstore.notification.domain.repositories.NotificationRepository
import com.fondstore.notification.data.utils.NotificationDataUtil
import com.fondstore.notification.domain.errors.NotificationError
import io.ktor.http.isSuccess

class KtorNotificationRepository(private val client: HttpClient) : NotificationRepository {
    override suspend fun getNotifications(token: String?): Result<List<Notification>, NotificationError> {
        return client.safeGet(
            urlString = NotificationDataUtil.NOTIFICATION_URL,
            tag = NotificationDataUtil.GET_NOTIFICATIONS_TAG,
            requestBlock = {
                authHeader(token = token)
            },
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    val notifications = response.body<List<NotificationResponse.Success>>()
                        .map(NotificationResponse.Success::toNotification)

                    Result.Success(notifications)
                } else {
                    Result.Error(response.body<NotificationResponse.Error>().toError())
                }
            }
        )
    }
}