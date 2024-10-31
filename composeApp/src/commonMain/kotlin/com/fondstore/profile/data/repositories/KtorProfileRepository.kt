package com.fondstore.profile.data.repositories

import com.fondstore.database.data.RealmDatabase
import com.fondstore.error.domain.models.Result
import com.fondstore.ktor.data.utils.authHeader
import com.fondstore.ktor.data.utils.safeGet
import com.fondstore.ktor.data.utils.safePost
import com.fondstore.ktor.data.utils.safeSubmitForm
import com.fondstore.profile.data.mappers.toError
import com.fondstore.profile.data.mappers.toInfo
import com.fondstore.profile.data.mappers.toProfile
import com.fondstore.profile.data.remote.responses.ProfileResponse
import com.fondstore.profile.data.remote.responses.UserInfoResponse
import com.fondstore.profile.data.utils.ProfileDataUtil
import com.fondstore.profile.domain.errors.ProfileError
import com.fondstore.profile.domain.errors.UserInfoError
import com.fondstore.profile.domain.models.Profile
import com.fondstore.profile.domain.models.UserInfo
import com.fondstore.profile.domain.repositories.ProfileRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.http.isSuccess
import io.ktor.http.parameters
import kotlinx.coroutines.flow.Flow

class KtorProfileRepository(private val database: RealmDatabase, private val client: HttpClient) :
    ProfileRepository {
    override fun getLocalProfileFlow(): Flow<Profile?> {
        return database.getProfileFlow()
    }

    override suspend fun insertLocalProfile(profile: Profile) {
        database.upsertProfile(profile)
    }

    override suspend fun deleteLocalProfile() {
        return database.deleteProfile()
    }

    override suspend fun getUserInfo(token: String?): Result<UserInfo, UserInfoError> {
        return client.safeGet(
            urlString = ProfileDataUtil.USER_INFO_URL,
            tag = ProfileDataUtil.GET_USER_INFO_TAG,
            requestBlock = {
                authHeader(token)
            },
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    Result.Success(response.body<UserInfoResponse.Success>().toInfo())
                } else {
                    Result.Error(response.body<UserInfoResponse.Error>().toError())
                }
            }
        )
    }

    override suspend fun getRemoteProfile(
        username: String,
        email: String,
        token: String?,
    ): Result<Profile?, ProfileError> {
        return client.safeGet(
            urlString = ProfileDataUtil.PROFILE_URL,
            tag = ProfileDataUtil.GET_PROFILE_TAG,
            requestBlock = {
                authHeader(token)
            },
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    val profile = response.body<List<ProfileResponse.Success>>().firstOrNull()
                        ?.toProfile(username = username, email = email)

                    Result.Success(profile)
                } else {
                    Result.Error(response.body<ProfileResponse.Error>().toError())
                }
            }
        )
    }

    override suspend fun createRemoteProfile(token: String?) {
        client.safePost<Unit, Unit>(
            urlString = ProfileDataUtil.PROFILE_URL,
            tag = ProfileDataUtil.CREATE_PROFILE_TAG,
            requestBlock = {
                authHeader(token)
            },
            responseBlock = {
                Result.Success(Unit)
            }
        )
    }

    override suspend fun updateProfile(
        firstname: String,
        lastname: String,
        phoneNumber: String,
        token: String?,
    ): Result<Boolean, ProfileError> {
        return client.safeSubmitForm(
            url = ProfileDataUtil.PROFILE_URL,
            tag = ProfileDataUtil.UPDATE_PROFILE_TAG,
            formParameters = parameters {
                append(ProfileDataUtil.FIRSTNAME_KEY, firstname)
                append(ProfileDataUtil.LASTNAME_KEY, lastname)
                append(ProfileDataUtil.PHONE_NUMBER_KEY, phoneNumber)
            },
            requestBlock = {
                authHeader(token = token)
            },
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    Result.Success(true)
                } else {
                    Result.Error(response.body<ProfileResponse.Error>().toError())
                }
            }
        )
    }
}