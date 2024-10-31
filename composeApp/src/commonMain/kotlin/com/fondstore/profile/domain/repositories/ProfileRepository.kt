package com.fondstore.profile.domain.repositories

import kotlinx.coroutines.flow.Flow
import com.fondstore.error.domain.models.Result
import com.fondstore.profile.domain.errors.ProfileError
import com.fondstore.profile.domain.errors.UserInfoError
import com.fondstore.profile.domain.models.Profile
import com.fondstore.profile.domain.models.UserInfo

interface ProfileRepository {
    fun getLocalProfileFlow(): Flow<Profile?>
    suspend fun insertLocalProfile(profile: Profile)
    suspend fun deleteLocalProfile()
    suspend fun getUserInfo(token: String?): Result<UserInfo, UserInfoError>
    suspend fun getRemoteProfile(username: String, email: String, token: String?): Result<Profile?, ProfileError>
    suspend fun createRemoteProfile(token: String?)
    suspend fun updateProfile(
        firstname: String,
        lastname: String,
        phoneNumber: String,
        token: String?,
    ): Result<Boolean, ProfileError>
}