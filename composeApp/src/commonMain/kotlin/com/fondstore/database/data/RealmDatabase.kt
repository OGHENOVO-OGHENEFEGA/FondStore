package com.fondstore.database.data

import com.fondstore.auth.data.local.AuthTokensObject
import com.fondstore.auth.data.mappers.toTokens
import com.fondstore.auth.domain.models.AuthTokens
import com.fondstore.launcher.data.local.LauncherObject
import com.fondstore.launcher.data.mappers.toState
import com.fondstore.launcher.domain.domain.LauncherState
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RealmDatabase {
    private val realm by lazy {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                LauncherObject::class,
                AuthTokensObject::class,
//                ProfileObject::class,
//                SettingsObject::class
            )
        ).deleteRealmIfMigrationNeeded().build()

        Realm.open(config)
    }

    fun getLauncherState(): LauncherState? {
        return realm.query<LauncherObject>().find().toState()
    }

    suspend fun upsertLauncherState(state: LauncherState) {
        realm.write {
            val currentLauncherObject = query<LauncherObject>().find().firstOrNull()

            if (currentLauncherObject == null) {
                val launcherObject = LauncherObject().apply {
                    isUserOnboard = state.isUserOnboard
                }

                copyToRealm(launcherObject)
            } else {
                currentLauncherObject.isUserOnboard = state.isUserOnboard
            }
        }
    }

    fun getAuthTokens(): AuthTokens? {
        return realm.query<AuthTokensObject>().find().toTokens()
    }

    fun getAuthTokensFlow(): Flow<AuthTokens?> {
        return realm.query<AuthTokensObject>().asFlow()
            .map(ResultsChange<AuthTokensObject>::toTokens)
    }

    suspend fun upsertAuthTokens(tokens: AuthTokens) {
        realm.write {
            val currentTokenObject = query<AuthTokensObject>().find().firstOrNull()

            if (currentTokenObject == null) {
                val tokensObject = AuthTokensObject().apply {
                    access = tokens.access
                    refresh = tokens.refresh
                    state = tokens.state.name
                }

                copyToRealm(tokensObject)
            } else {
                currentTokenObject.access = tokens.access
                currentTokenObject.refresh = tokens.refresh
                currentTokenObject.state = tokens.state.name
            }
        }
    }

    suspend fun deleteAuthTokens() {
        realm.write {
            query<AuthTokensObject>().find().forEach { authTokensObject ->
                delete(authTokensObject)
            }
        }
    }
//    fun getSettings(): Settings {
//        return realm.query<SettingsObject>().find().toSettings()
//    }
//
//    fun getSettingsFlow(): Flow<Settings> {
//        return realm.query<SettingsObject>().asFlow().map(ResultsChange<SettingsObject>::toSettings)
//    }
//
//    suspend fun upsertSettings(settings: Settings) {
//        realm.write {
//            val currentSettingsObject = query<SettingsObject>().find().firstOrNull()
//
//            if (currentSettingsObject == null) {
//                val settingsObject = SettingsObject().apply {
//                    isBalanceVisible = settings.isBalanceVisible
//                    isAutoLogoutEnabled = settings.isAutoLogoutEnabled
//                    autoLogoutDuration = settings.autoLogoutDuration?.name
//                    isDeviceSecurityEnabled = settings.isDeviceSecurityEnabled
//                    deviceSecurityTiming = settings.deviceSecurityTiming?.name
//                }
//
//                copyToRealm(settingsObject)
//            } else {
//                currentSettingsObject.isBalanceVisible = settings.isBalanceVisible
//                currentSettingsObject.isAutoLogoutEnabled = settings.isAutoLogoutEnabled
//                currentSettingsObject.autoLogoutDuration = settings.autoLogoutDuration?.name
//                currentSettingsObject.isDeviceSecurityEnabled = settings.isDeviceSecurityEnabled
//                currentSettingsObject.deviceSecurityTiming = settings.deviceSecurityTiming?.name
//            }
//        }
//    }
//
//    fun getEmail(): String? {
//        return realm.query<ProfileObject>().find().firstOrNull()?.email
//    }
//
//    suspend fun setEmail(email: String) {
//        realm.write {
//            val currentProfileObject = query<ProfileObject>().find().firstOrNull()
//
//            if (currentProfileObject == null) {
//                val profileObject = ProfileObject().apply {
//                    this.email = email
//                }
//
//                copyToRealm(profileObject)
//            } else {
//                currentProfileObject.email = email
//            }
//        }
//    }
}