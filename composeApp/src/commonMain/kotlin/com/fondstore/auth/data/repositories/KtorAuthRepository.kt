package com.fondstore.auth.data.repositories

import com.fondstore.auth.domain.models.AuthTokens
import com.fondstore.auth.domain.models.AuthTokensState
import com.fondstore.auth.domain.repositories.AuthRepository
import com.fondstore.database.RealmDatabase
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow

class KtorAuthRepository(private val database: RealmDatabase, private val client: HttpClient) :
    AuthRepository {
    override fun isUserSignedIn(): Boolean {
        val tokenState = database.getAuthTokens()?.state
        return tokenState != null && tokenState != AuthTokensState.UNAVAILABLE
    }

    override fun getLocalAuthTokens(): AuthTokens? {
        return database.getAuthTokens()
    }

    override fun getLocalAuthTokensFlow(): Flow<AuthTokens?> {
        return database.getAuthTokensFlow()
    }

    override suspend fun insertLocalAuthTokens(tokens: AuthTokens) {
        database.upsertAuthTokens(tokens = tokens)
    }

    override suspend fun deleteLocalAuthTokens() {
        database.deleteAuthTokens()
    }
}