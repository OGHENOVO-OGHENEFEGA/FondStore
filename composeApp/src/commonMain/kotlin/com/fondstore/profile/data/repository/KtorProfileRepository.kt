package profile.data.repository

import app.cash.sqldelight.coroutines.asFlow
import com.fondstore.AppQueries
import error.QueryResult
import error.authHeader
import error.safeGet
import error.safePost
import error.safeSubmitForm
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.http.HttpStatusCode
import io.ktor.http.parameters
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import profile.data.mapper.toInfo
import profile.data.mapper.toProfile
import profile.data.remote.responses.ProfileResponse
import profile.data.remote.responses.UserInfoResponse
import profile.domain.models.Profile
import profile.domain.models.UserInfo
import profile.domain.repository.ProfileRepository
import com.fondstore.profile.data.utils.ProfileDataUtil

class KtorProfileRepository(private val queries: AppQueries, private val client: HttpClient) :
    ProfileRepository {
    override suspend fun getUserInfo(token: String?): QueryResult<UserInfo> {
        return client.safeGet(
            urlString = ProfileDataUtil.USER_INFO_URL,
            requestBlock = {
                authHeader(token)
            }
        ) { response ->
            response.body<UserInfoResponse>().toInfo()
        }
    }

    override suspend fun getRemoteProfile(token: String?): QueryResult<Profile?> {
        return client.safeGet(
            urlString = ProfileDataUtil.PROFILE_URL,
            requestBlock = {
                authHeader(token)
            }
        ) { response ->
            var profile = response.body<List<ProfileResponse>>()
                .map { profileResponse ->
                    profileResponse.toProfile()
                }.firstOrNull()

            if (profile != null) {
                val userInfoResult = getUserInfo(token)

                userInfoResult.onSuccess { info ->
                    profile = profile?.copy(username = info.username, email = info.email)
                }

                profile
            } else {
                profile
            }
        }
    }

    override suspend fun createRemoteProfile(token: String?) {
        client.safePost(
            urlString = ProfileDataUtil.PROFILE_URL,
            requestBlock = {
                authHeader(token)
            }
        ) {

        }
    }

    override suspend fun updateProfile(
        firstname: String,
        lastname: String,
        phoneNumber: String,
        token: String?
    ): QueryResult<Boolean> {
        return client.safeSubmitForm(
            url = ProfileDataUtil.PROFILE_URL,
            parameters {
                append(ProfileDataUtil.FIRSTNAME_KEY, firstname)
                append(ProfileDataUtil.LASTNAME_KEY, lastname)
                append(ProfileDataUtil.PHONE_NUMBER_KEY, phoneNumber)
            },
            requestBlock = {
                authHeader(token = token)
            }
        ) {
            it.status == HttpStatusCode.Created
        }
    }

    override suspend fun getLocalProfileFlow(): Flow<Profile> {
        return queries.getProfile()
            .asFlow()
            .map { query ->
                query.executeAsOneOrNull()?.toProfile() ?: Profile()
            }
    }

    override suspend fun insertLocalProfile(profile: Profile) {
        queries.insertProfile(
            uid = profile.id.toLong(),
            image = profile.image,
            username = profile.username,
            email = profile.email,
            firstname = profile.firstname,
            lastname = profile.lastname,
            phoneNumber = profile.phoneNumber
        )
    }

    override suspend fun deleteLocalProfile() {
        queries.deleteProfile()
    }
}