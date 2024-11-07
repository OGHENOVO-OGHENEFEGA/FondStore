package com.fondstore.termsAndConditions.data.repositories

import com.fondstore.error.Result
import com.fondstore.ktor.safeGet
import com.fondstore.termsAndConditions.data.mappers.toError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import com.fondstore.termsAndConditions.data.mappers.toTermsAndCondition
import com.fondstore.termsAndConditions.data.remote.responses.TermsAndConditionResponse
import com.fondstore.termsAndConditions.domain.models.TermsAndCondition
import com.fondstore.termsAndConditions.domain.repositories.TermsAndConditionRepository
import com.fondstore.termsAndConditions.data.utils.TermsAndConditionDataUtil
import com.fondstore.termsAndConditions.domain.errors.TermsAndConditionError
import io.ktor.http.isSuccess

class KtorTermsAndConditionRepository(private val client: HttpClient) :
    TermsAndConditionRepository {
    override suspend fun getTermsAndCondition(): Result<TermsAndCondition?, TermsAndConditionError> {
        return client.safeGet(
            urlString = TermsAndConditionDataUtil.TERMS_AND_CONDITION_URL,
            tag = TermsAndConditionDataUtil.GET_TERMS_AND_CONDITIONS_TAG,
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    val termsAndCondition =
                        response.body<List<TermsAndConditionResponse.Success>>().firstOrNull()
                            ?.toTermsAndCondition()
                    Result.Success(termsAndCondition)
                } else {
                    Result.Error(response.body<TermsAndConditionResponse.Error>().toError())
                }
            }
        )
    }
}