package com.fondstore.launcher.domain.repositories

interface LauncherRepository {
    fun isUserOnboard(): Boolean
    suspend fun setUserOnboard()
}