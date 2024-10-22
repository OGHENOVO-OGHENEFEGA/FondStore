package com.fondstore.di

import com.fondstore.database.data.RealmDatabase
import com.fondstore.onboarding.OnboardingScreenPage
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.elevate_your_experience
import fondstore.composeapp.generated.resources.embrace_your_unique_style
import fondstore.composeapp.generated.resources.find_refined_accessories
import fondstore.composeapp.generated.resources.find_refined_style
import fondstore.composeapp.generated.resources.onboarding_image_1
import fondstore.composeapp.generated.resources.onboarding_image_2
import fondstore.composeapp.generated.resources.onboarding_image_3
import fondstore.composeapp.generated.resources.refine_your_look
import fondstore.composeapp.generated.resources.unleash_your_style
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.HttpTimeoutConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreModule = module {
    singleOf(::RealmDatabase)

    single {
        HttpClient(get()) {
            defaultRequest {
                url("https://api.fondstore.com")
                contentType(ContentType.Application.Json)
            }

            install(HttpTimeout) {
                requestTimeoutMillis = HttpTimeoutConfig.INFINITE_TIMEOUT_MS
                connectTimeoutMillis = HttpTimeoutConfig.INFINITE_TIMEOUT_MS
                socketTimeoutMillis = HttpTimeoutConfig.INFINITE_TIMEOUT_MS
            }

            val defaultJson = Json {
                isLenient = true
                prettyPrint = true
                coerceInputValues = true
                ignoreUnknownKeys = true
                encodeDefaults = true
            }

            install(ContentNegotiation) {
                json(defaultJson)
            }

            install(WebSockets) {
                contentConverter = KotlinxWebsocketSerializationConverter(defaultJson)
            }

//            HttpResponseValidator {
//                validateResponse { response ->
//                    if (response.status == HttpStatusCode.Unauthorized) {
//                        val database = get<RealmDatabase>()
//                        val tokens = database.getAuthTokens()
//
//                        if (tokens?.state == AuthTokensState.AVAILABLE) {
//                            val message = try {
//                                response.body<InvalidAuthTokenResponse>()
//                                    .toToken().messages.firstOrNull()
//                            } catch (_: Exception) {
//                                null
//                            }
//
//
//                            if (message?.tokenType?.lowercase()?.contains("acc") == true) {
//                                val invalidTokens =
//                                    tokens.copy(state = AuthTokensState.INACTIVE)
//                                database.upsertAuthTokens(tokens = invalidTokens)
//                            }
//                        }
//                    } else if (!response.status.isSuccess()) {
//                        val error = try {
//                            response.body<PinErrorResponse>()
//                        } catch (_: Exception) {
//                            null
//                        }
//
//                        if (!error?.pin.isNullOrBlank()) {
//                            val database = get<RealmDatabase>()
//                            val pinState = database.getPinState()
//
//                            val newPinState = pinState?.copy(pin = null)
//                                ?: PinState(isPinCreated = false, pin = null)
//
//                            database.upsertPinState(state = newPinState)
//                        }
//                    }
//                }
//            }
        }
    }

    factory {
        listOf(
            OnboardingScreenPage(
                image = Res.drawable.onboarding_image_1,
                heading = Res.string.refine_your_look,
                text = Res.string.embrace_your_unique_style
            ),
            OnboardingScreenPage(
                image = Res.drawable.onboarding_image_2,
                heading = Res.string.unleash_your_style,
                text = Res.string.find_refined_accessories
            ),
            OnboardingScreenPage(
                image = Res.drawable.onboarding_image_3,
                heading = Res.string.elevate_your_experience,
                text = Res.string.find_refined_style
            )
        )
    }
}