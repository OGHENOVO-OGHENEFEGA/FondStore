package com.fondstore.notification.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.fondstore.auth.domain.repositories.AuthRepository
import com.fondstore.notification.domain.repositories.NotificationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotificationsScreenModel(
    private val authRepository: AuthRepository,
    private val notificationRepository: NotificationRepository
) : StateScreenModel<NotificationsScreenState>(NotificationsScreenState()) {

    init {
        getNotifications()
    }

    fun onEvent(event: NotificationsScreenEvent) {
        when (event) {
            NotificationsScreenEvent.RefreshScreen -> refreshScreen()
            NotificationsScreenEvent.CloseScreen -> closeScreen()
        }
    }

    private fun refreshScreen() {
        screenModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                mutableState.update {
                    it.copy(isRefreshingScreen = true)
                }
            }

            val result =
                notificationRepository.getNotifications(token = authRepository.getLocalAuthTokens()?.access)

            withContext(Dispatchers.Main + NonCancellable) {
                mutableState.update {
                    it.copy(isRefreshingScreen = false, result = result)
                }
            }
        }
    }

    private fun getNotifications() {
        screenModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                mutableState.update {
                    it.copy(isGettingNotifications = true)
                }
            }

            val result =
                notificationRepository.getNotifications(token = authRepository.getLocalAuthTokens()?.access)

            withContext(Dispatchers.Main + NonCancellable) {
                mutableState.update {
                    it.copy(isGettingNotifications = false, result = result)
                }
            }
        }
    }

    private fun closeScreen() {
        mutableState.update {
            it.copy(isActive = false)
        }
    }
}