package com.fondstore.returnAndExchangePolicy.data.repositories

import com.fondstore.error.domain.models.Result
import com.fondstore.ktor.data.utils.safeGet
import com.fondstore.returnAndExchangePolicy.data.mappers.toError
import com.fondstore.returnAndExchangePolicy.data.mappers.toPolicy
import com.fondstore.returnAndExchangePolicy.data.remote.responses.ReturnAndExchangePolicyResponse
import com.fondstore.returnAndExchangePolicy.data.utils.ReturnAndExchangePolicyDataUtil
import com.fondstore.returnAndExchangePolicy.domain.errors.ReturnAndExchangePolicyError
import com.fondstore.returnAndExchangePolicy.domain.models.ReturnAndExchangePolicy
import com.fondstore.returnAndExchangePolicy.domain.repositories.ReturnAndExchangePolicyRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.http.isSuccess

class KtorReturnAndExchangePolicyRepository(private val client: HttpClient) :
    ReturnAndExchangePolicyRepository {
    override suspend fun getReturnAndExchangePolicy(): Result<ReturnAndExchangePolicy?, ReturnAndExchangePolicyError> {
        return client.safeGet(
            urlString = ReturnAndExchangePolicyDataUtil.RETURN_AND_EXCHANGE_POLICY_URL,
            tag = ReturnAndExchangePolicyDataUtil.GET_RETURN_AND_EXCHANGE_POLICY_TAG,
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    Result.Success(
                        response.body<List<ReturnAndExchangePolicyResponse.Success>>().firstOrNull()
                            ?.toPolicy()
                    )
                } else {
                    Result.Error(response.body<ReturnAndExchangePolicyResponse.Error>().toError())
                }
            }
        )
    }
}