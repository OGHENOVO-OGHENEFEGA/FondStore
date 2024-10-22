package com.fondstore.auth.domain.repositories

import com.fondstore.auth.domain.models.AuthTokens
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun isUserSignedIn(): Boolean
    fun getLocalAuthTokens(): AuthTokens?
    fun getLocalAuthTokensFlow(): Flow<AuthTokens?>
    suspend fun insertLocalAuthTokens(tokens: AuthTokens)
    suspend fun deleteLocalAuthTokens()
}