package com.fondstore.database.data

import com.fondstore.launcher.data.local.LauncherObject
import com.fondstore.launcher.data.mappers.toState
import com.fondstore.launcher.domain.domain.LauncherState
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query

class RealmDatabase {
    private val realm by lazy {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                LauncherObject::class,
//                AuthTokensObject::class,
//                PinObject::class,
//                PushNotificationsObject::class,
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

//    fun getAuthTokens(): AuthTokens? {
//        return realm.query<AuthTokensObject>().find().toTokens()
//    }
//
//    fun getAuthTokensFlow(): Flow<AuthTokens?> {
//        return realm.query<AuthTokensObject>().asFlow()
//            .map(ResultsChange<AuthTokensObject>::toTokens)
//    }
//
//    suspend fun upsertAuthTokens(tokens: AuthTokens) {
//        realm.write {
//            val currentTokenObject = query<AuthTokensObject>().find().firstOrNull()
//
//            if (currentTokenObject == null) {
//                val tokensObject = AuthTokensObject().apply {
//                    access = tokens.access
//                    refresh = tokens.refresh
//                    state = tokens.state.name
//                }
//
//                copyToRealm(tokensObject)
//            } else {
//                currentTokenObject.access = tokens.access
//                currentTokenObject.refresh = tokens.refresh
//                currentTokenObject.state = tokens.state.name
//            }
//        }
//    }
//
//    suspend fun deleteAuthTokens() {
//        realm.write {
//            query<AuthTokensObject>().find().forEach { authTokensObject ->
//                delete(authTokensObject)
//            }
//        }
//    }
//
//    fun getPinState(): PinState? {
//        return realm.query<PinObject>().find().toState()
//    }
//
//    fun getPinStateFlow(): Flow<PinState?> {
//        return realm.query<PinObject>().asFlow().map(ResultsChange<PinObject>::toState)
//    }
//
//    suspend fun upsertPinState(state: PinState) {
//        realm.write {
//            val currentPinObject = query<PinObject>().find().firstOrNull()
//
//            if (currentPinObject == null) {
//                val pinObject = PinObject().apply {
//                    isPinCreated = state.isPinCreated
//                    pin = state.pin
//                }
//
//                copyToRealm(pinObject)
//            } else {
//                currentPinObject.isPinCreated = state.isPinCreated
//                currentPinObject.pin = state.pin
//            }
//        }
//    }
//
//    suspend fun deletePin() {
//        realm.write {
//            query<PinObject>().find().forEach { pinObject ->
//                delete(pinObject)
//            }
//        }
//    }
//
//    fun getPushNotificationsState(): PushNotificationsState? {
//        return realm.query<PushNotificationsObject>().find().toState()
//    }
//
//    fun getPushNotificationsStateFlow(): Flow<PushNotificationsState?> {
//        return realm.query<PushNotificationsObject>().asFlow()
//            .map(ResultsChange<PushNotificationsObject>::toState)
//    }
//
//    suspend fun upsertPushNotificationsState(state: PushNotificationsState) {
//        realm.write {
//            val currentPushNotificationsObject = query<PushNotificationsObject>().find().firstOrNull()
//
//            if (currentPushNotificationsObject == null) {
//                val tokensObject = PushNotificationsObject().apply {
//                    token = state.token
//                }
//
//                copyToRealm(tokensObject)
//            } else {
//                currentPushNotificationsObject.token = state.token
//            }
//        }
//    }
//
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