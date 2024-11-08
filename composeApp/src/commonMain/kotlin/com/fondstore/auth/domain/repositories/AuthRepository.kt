package com.fondstore.auth.domain.repositories

import com.fondstore.auth.domain.errors.ResetPasswordError
import com.fondstore.auth.domain.errors.SignInError
import com.fondstore.auth.domain.errors.SignUpError
import com.fondstore.auth.domain.models.AuthTokens
import com.fondstore.auth.domain.models.ResetPasswordInfo
import com.fondstore.auth.domain.models.SignInInfo
import com.fondstore.auth.domain.models.SignUpInfo
import com.fondstore.error.Result
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun isUserSignedIn(): Boolean
    fun getLocalAuthTokens(): AuthTokens?
    fun getLocalAuthTokensFlow(): Flow<AuthTokens?>
    suspend fun insertLocalAuthTokens(tokens: AuthTokens)
    suspend fun deleteLocalAuthTokens()
    suspend fun signIn(email: String, password: String): Result<SignInInfo, SignInError>
    suspend fun signUp(
        username: String,
        email: String,
        password: String,
        confirmPassword: String,
    ): Result<SignUpInfo, SignUpError>

    suspend fun resetPassword(email: String): Result<ResetPasswordInfo, ResetPasswordError>
}