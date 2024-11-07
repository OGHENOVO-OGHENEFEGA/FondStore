package com.fondstore.contactUs.data.repositories

import com.fondstore.contactUs.data.mappers.toError
import com.fondstore.contactUs.data.mappers.toHelpCentre
import com.fondstore.contactUs.data.mappers.toSocial
import com.fondstore.contactUs.data.remote.responses.HelpCentreResponse
import com.fondstore.contactUs.data.remote.responses.SocialResponse
import com.fondstore.contactUs.domain.models.HelpCentre
import com.fondstore.contactUs.domain.models.Social
import com.fondstore.contactUs.domain.repositories.ContactUsRepository
import com.fondstore.contactUs.data.utils.ContactUsDataUtil
import com.fondstore.contactUs.domain.errors.HelpCentreError
import com.fondstore.contactUs.domain.errors.SocialError
import com.fondstore.error.Result
import com.fondstore.ktor.safeGet
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.http.isSuccess

class KtorContactUsRepository(private val client: HttpClient) : ContactUsRepository {
    override suspend fun getSocials(): Result<List<Social>, SocialError> {
        return client.safeGet(
            urlString = ContactUsDataUtil.SOCIALS_URL,
            tag = ContactUsDataUtil.GET_SOCIALS_TAG,
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    Result.Success(
                        response.body<List<SocialResponse.Success>>()
                            .map(SocialResponse.Success::toSocial)
                    )
                } else {
                    Result.Error(response.body<SocialResponse.Error>().toError())
                }
            }
        )
    }

    override suspend fun getHelpCentre(): Result<HelpCentre?, HelpCentreError> {
        return client.safeGet(
            urlString = ContactUsDataUtil.HELP_CENTRE_URL,
            tag = ContactUsDataUtil.GET_HELP_CENTRE_TAG,
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    Result.Success(
                        response.body<List<HelpCentreResponse.Success>>().firstOrNull()
                            ?.toHelpCentre()
                    )
                } else {
                    Result.Error(response.body<HelpCentreResponse.Error>().toError())
                }
            }
        )
    }
}