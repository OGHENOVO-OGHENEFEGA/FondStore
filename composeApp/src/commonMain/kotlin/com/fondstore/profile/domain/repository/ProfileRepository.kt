package profile.domain.repository

import error.QueryResult
import kotlinx.coroutines.flow.Flow
import profile.domain.models.Profile
import profile.domain.models.UserInfo

interface ProfileRepository {
    suspend fun getUserInfo(token: String?): QueryResult<UserInfo>
    suspend fun getRemoteProfile(token: String?): QueryResult<Profile?>
    suspend fun createRemoteProfile(token: String?)
    suspend fun updateProfile(firstname: String, lastname: String, phoneNumber: String, token: String?): QueryResult<Boolean>
    suspend fun getLocalProfileFlow(): Flow<Profile>
    suspend fun insertLocalProfile(profile: Profile)
    suspend fun deleteLocalProfile()
}