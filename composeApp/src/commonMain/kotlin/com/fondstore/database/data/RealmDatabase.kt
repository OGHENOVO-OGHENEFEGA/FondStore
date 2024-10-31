package com.fondstore.database.data

import com.fondstore.auth.data.local.AuthTokensObject
import com.fondstore.auth.data.mappers.toTokens
import com.fondstore.auth.domain.models.AuthTokens
import com.fondstore.launcher.data.local.LauncherObject
import com.fondstore.launcher.data.mappers.toState
import com.fondstore.launcher.domain.domain.LauncherState
import com.fondstore.profile.data.local.ProfileObject
import com.fondstore.profile.data.mappers.toProfile
import com.fondstore.profile.domain.models.Profile
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
                ProfileObject::class
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

    fun getProfileFlow(): Flow<Profile?> {
        return realm.query<ProfileObject>().asFlow()
            .map(ResultsChange<ProfileObject>::toProfile)
    }

    suspend fun upsertProfile(profile: Profile) {
        realm.write {
            val currentProfileObject = query<ProfileObject>().find().firstOrNull()

            if (currentProfileObject == null) {
                val profileObject = ProfileObject().apply {
                    id = profile.id
                    image = profile.image
                    username = profile.username
                    email = profile.email
                    firstname = profile.firstname
                    lastname = profile.lastname
                    phoneNumber = profile.phoneNumber
                }

                copyToRealm(profileObject)
            } else {
                currentProfileObject.id = profile.id
                currentProfileObject.image = profile.image
                currentProfileObject.username = profile.username
                currentProfileObject.email = profile.email
                currentProfileObject.firstname = profile.firstname
                currentProfileObject.lastname = profile.lastname
                currentProfileObject.phoneNumber = profile.phoneNumber
            }
        }
    }

    suspend fun deleteProfile() {
        realm.write {
            query<ProfileObject>().find().forEach { profileObject ->
                delete(profileObject)
            }
        }
    }
}