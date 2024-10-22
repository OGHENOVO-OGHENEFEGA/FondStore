package com.fondstore.notification.presentation

sealed interface NotificationsScreenEvent {
    data object RefreshScreen : NotificationsScreenEvent
    data object CloseScreen : NotificationsScreenEvent
}