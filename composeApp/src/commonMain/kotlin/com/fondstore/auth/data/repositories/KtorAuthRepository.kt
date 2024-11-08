package com.fondstore.auth.data.repositories

import com.fondstore.auth.data.mappers.toError
import com.fondstore.auth.data.mappers.toInfo
import com.fondstore.auth.data.remote.requests.ResetPasswordRequest
import com.fondstore.auth.data.remote.requests.SignInRequest
import com.fondstore.auth.data.remote.requests.SignUpRequest
import com.fondstore.auth.data.remote.responses.ResetPasswordErrorResponse
import com.fondstore.auth.data.remote.responses.SignInResponse
import com.fondstore.auth.data.remote.responses.SignUpResponse
import com.fondstore.auth.data.utils.AuthDataUtil
import com.fondstore.auth.domain.errors.ResetPasswordError
import com.fondstore.auth.domain.errors.SignInError
import com.fondstore.auth.domain.errors.SignUpError
import com.fondstore.auth.domain.models.AuthTokens
import com.fondstore.auth.domain.models.AuthTokensState
import com.fondstore.auth.domain.models.ResetPasswordInfo
import com.fondstore.auth.domain.models.SignInInfo
import com.fondstore.auth.domain.models.SignUpInfo
import com.fondstore.auth.domain.repositories.AuthRepository
import com.fondstore.database.RealmDatabase
import com.fondstore.error.Result
import com.fondstore.ktor.safePost
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.setBody
import io.ktor.http.isSuccess
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

    override suspend fun signIn(email: String, password: String): Result<SignInInfo, SignInError> {
        return client.safePost(
            urlString = AuthDataUtil.SIGN_IN_URL,
            tag = AuthDataUtil.SIGN_IN_TAG,
            requestBlock = {
                setBody(SignInRequest(email = email, password = password))
            },
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    Result.Success(response.body<SignInResponse.Success>().toInfo())
                } else {
                    Result.Error(response.body<SignInResponse.Error>().toError())
                }
            }
        )
    }

    override suspend fun signUp(
        username: String,
        email: String,
        password: String,
        confirmPassword: String,
    ): Result<SignUpInfo, SignUpError> {
        return client.safePost(
            urlString = AuthDataUtil.SIGN_UP_URL,
            tag = AuthDataUtil.SIGN_UP_TAG,
            requestBlock = {
                setBody(
                    SignUpRequest(
                        username = username,
                        email = email,
                        password = password,
                        rePassword = confirmPassword
                    )
                )
            },
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    Result.Success(response.body<SignUpResponse.Success>().toInfo())
                } else {
                    Result.Error(response.body<SignUpResponse.Error>().toError())
                }
            }
        )
    }

    override suspend fun resetPassword(email: String): Result<ResetPasswordInfo, ResetPasswordError> {
        return client.safePost(
            urlString = AuthDataUtil.RESET_PASSWORD_URL,
            tag = AuthDataUtil.RESET_PASSWORD_TAG,
            requestBlock = {
                setBody(ResetPasswordRequest(email = email))
            },
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    Result.Success(ResetPasswordInfo(email = email))
                } else {
                    val error = try {
                        response.body<ResetPasswordErrorResponse>().toError()
                    } catch (_: Exception) {
                        ResetPasswordError(email = response.body<List<String>>().joinToString("\n"))
                    }

                    Result.Error(error = error)
                }
            }
        )
    }
}