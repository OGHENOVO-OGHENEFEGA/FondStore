package com.fondstore.privacyPolicy.data.repositories

import com.fondstore.error.domain.models.Result
import com.fondstore.ktor.data.utils.safeGet
import com.fondstore.privacyPolicy.data.mappers.toError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import com.fondstore.privacyPolicy.data.mappers.toPrivacyPolicy
import com.fondstore.privacyPolicy.data.remote.responses.PrivacyPolicyResponse
import com.fondstore.privacyPolicy.domain.models.PrivacyPolicy
import com.fondstore.privacyPolicy.domain.repositories.PrivacyPolicyRepository
import com.fondstore.privacyPolicy.data.utils.PrivacyPolicyDataUtil
import com.fondstore.privacyPolicy.domain.errors.PrivacyPolicyError
import io.ktor.http.isSuccess

class KtorPrivacyPolicyRepository(private val client: HttpClient) : PrivacyPolicyRepository {
    override suspend fun getPrivacyPolicy(): Result<PrivacyPolicy?, PrivacyPolicyError> {
        return client.safeGet(
            urlString = PrivacyPolicyDataUtil.PRIVACY_POLICY_URL,
            tag = PrivacyPolicyDataUtil.GET_PRIVACY_POLICY_TAG,
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    val policy = response.body<List<PrivacyPolicyResponse.Success>>().firstOrNull()
                        ?.toPrivacyPolicy()
                    Result.Success(policy)
                } else {
                    Result.Error(response.body<PrivacyPolicyResponse.Error>().toError())
                }
            }
        )
    }
}