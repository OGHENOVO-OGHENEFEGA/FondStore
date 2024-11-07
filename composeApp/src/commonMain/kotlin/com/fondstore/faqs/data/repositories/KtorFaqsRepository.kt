package com.fondstore.faqs.data.repositories

import com.fondstore.error.Result
import com.fondstore.faqs.data.mappers.toError
import com.fondstore.faqs.data.mappers.toFaq
import com.fondstore.faqs.data.remote.responses.FaqResponse
import com.fondstore.faqs.domain.models.Faq
import com.fondstore.faqs.domain.repositories.FaqsRepository
import com.fondstore.faqs.data.utils.FaqsDataUtil
import com.fondstore.faqs.domain.errors.FaqsError
import com.fondstore.ktor.safeGet
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.http.isSuccess

class KtorFaqsRepository(private val client: HttpClient) : FaqsRepository {
    override suspend fun getFaqs(): Result<List<Faq>, FaqsError> {
        return client.safeGet(
            urlString = FaqsDataUtil.FAQS_URL,
            tag = FaqsDataUtil.GET_FAQS_TAG,
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    Result.Success(
                        response.body<List<FaqResponse.Success>>().map(FaqResponse.Success::toFaq)
                    )
                } else {
                    Result.Error(response.body<FaqResponse.Error>().toError())
                }
            }
        )
    }
}