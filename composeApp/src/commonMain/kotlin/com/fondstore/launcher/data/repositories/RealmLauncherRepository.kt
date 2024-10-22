package com.fondstore.launcher.data.repositories

import com.fondstore.database.data.RealmDatabase
import com.fondstore.launcher.domain.domain.LauncherState
import com.fondstore.launcher.domain.repositories.LauncherRepository

class RealmLauncherRepository(private val database: RealmDatabase) : LauncherRepository {
    override fun isUserOnboard(): Boolean {
        return database.getLauncherState()?.isUserOnboard ?: false
    }

    override suspend fun setUserOnboard() {
        database.upsertLauncherState(state = LauncherState(isUserOnboard = true))
    }
}