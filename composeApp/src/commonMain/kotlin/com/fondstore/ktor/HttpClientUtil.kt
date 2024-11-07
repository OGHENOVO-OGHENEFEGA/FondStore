package com.fondstore.ktor

import com.fondstore.auth.data.utils.AuthDataUtil
import com.fondstore.error.Result
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.receiveDeserialized
import io.ktor.client.plugins.websocket.sendSerialized
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpMessageBuilder
import io.ktor.http.Parameters
import io.ktor.http.content.PartData
import io.ktor.util.reflect.typeInfo

suspend fun <D, E> HttpClient.safeGet(
    urlString: String,
    tag: String,
    requestBlock: HttpRequestBuilder.() -> Unit = {},
    responseBlock: suspend (HttpResponse) -> Result<D, E>,
    exceptionBlock: suspend (Exception) -> Result<D, E> = { exception -> Result.Exception(exception)}
): Result<D, E> {
    return try {
        println("SafeGet: $tag $urlString")
        val response = get(urlString = urlString, block = requestBlock)
        println("SafeGetResponse: $tag ${response.status} ${response.bodyAsText()}")
        responseBlock(response)
    } catch (exception: Exception) {
        println("SafeGetException: $tag ${exception::class} ${exception.message}")
        exceptionBlock(exception)
    }
}

suspend fun <D, E> HttpClient.safePost(
    urlString: String,
    tag: String,
    requestBlock: HttpRequestBuilder.() -> Unit = {},
    responseBlock: suspend (HttpResponse) -> Result<D, E>,
    exceptionBlock: suspend (Exception) -> Result<D, E> = { exception -> Result.Exception(exception)}
): Result<D, E> {
    return try {
        println("SafePost: $tag $urlString")
        val response = post(urlString = urlString, block = requestBlock)
        println("SafePostResponse: $tag ${response.status} ${response.bodyAsText()}")
        responseBlock(response)
    } catch (exception: Exception) {
        println("SafePostException: $tag ${exception::class} ${exception.message}")
        exceptionBlock(exception)
    }
}

suspend fun <D, E> HttpClient.safeDelete(
    urlString: String,
    tag: String,
    requestBlock: HttpRequestBuilder.() -> Unit = {},
    responseBlock: suspend (HttpResponse) -> Result<D, E>,
    exceptionBlock: suspend (Exception) -> Result<D, E> = { exception -> Result.Exception(exception)}
): Result<D, E> {
    return try {
        println("SafeDelete: $tag $urlString")
        val response = delete(urlString = urlString, block = requestBlock)
        println("SafeDeleteResponse: $tag ${response.status} ${response.bodyAsText()}")
        responseBlock(response)
    } catch (exception: Exception) {
        println("SafeDeleteException: $tag ${exception::class} ${exception.message}")
        exceptionBlock(exception)
    }
}

suspend fun <D, E> HttpClient.safeSubmitForm(
    url: String,
    tag: String,
    formParameters: Parameters = Parameters.Empty,
    encodeInQuery: Boolean = false,
    requestBlock: HttpRequestBuilder.() -> Unit = {},
    responseBlock: suspend (HttpResponse) -> Result<D, E>,
    exceptionBlock: suspend (Exception) -> Result<D, E> = { exception -> Result.Exception(exception)}
): Result<D, E> {
    return try {
        println("SafeSubmitForm: $tag $url")

        val response = submitForm(
            url = url,
            formParameters = formParameters,
            encodeInQuery = encodeInQuery,
            block = requestBlock
        )

        println("SafeSubmitFormResponse: $tag ${response.status} ${response.bodyAsText()}")
        responseBlock(response)
    } catch (exception: Exception) {
        println("SafeSubmitFormException: $tag ${exception::class} ${exception.message}")
        exceptionBlock(exception)
    }
}

suspend fun <D, E> HttpClient.safeSubmitFormWithBinaryData(
    url: String,
    tag: String,
    formData: List<PartData>,
    requestBlock: HttpRequestBuilder.() -> Unit = {},
    responseBlock: suspend (HttpResponse) -> Result<D, E>,
    exceptionBlock: suspend (Exception) -> Result<D, E> = { exception -> Result.Exception(exception)}
): Result<D, E> {
    return try {
        println("SafeSubmitFormWithBinaryData: $tag $url")

        val response =
            submitFormWithBinaryData(url = url, formData = formData, block = requestBlock)

        println("SafeSubmitFormWithBinaryDataResponse: $tag ${response.status} ${response.bodyAsText()}")
        responseBlock(response)
    } catch (exception: Exception) {
        println("SafeSubmitFormWithBinaryDataException: $tag ${exception::class} ${exception.message}")
        exceptionBlock(exception)
    }
}

suspend fun HttpClient.safeWebSocket(
    urlString: String,
    tag: String,
    requestBlock: HttpRequestBuilder.() -> Unit = {},
    sessionBlock: suspend DefaultClientWebSocketSession.() -> Unit
) {
    return try {
        webSocket(
            urlString = urlString,
            request = requestBlock,
            block = sessionBlock
        )

    } catch (exception: Exception) {
        println("SafeWebSocketException: $tag ${exception::class} ${exception.message}")
    }
}

suspend inline fun <reified D> DefaultClientWebSocketSession.safeSendSerialized(
    data: D,
    tag: String
) {
    try {
        sendSerialized(data, typeInfo<D>())
    } catch (exception: Exception) {
        println("SafeSendSerializedException: $tag ${exception::class} ${exception.message}")
    }
}

suspend inline fun <reified D> DefaultClientWebSocketSession.safeReceiveDeserialized(tag: String): D? {
    return try {
        receiveDeserialized(typeInfo<D>())
    } catch (exception: Exception) {
        println("SafeReceiveSerializedException: $tag ${exception::class} ${exception.message}")
        null
    }
}

fun HttpMessageBuilder.authHeader(token: String?) {
    if (!token.isNullOrBlank()) {
        header(
            key = AuthDataUtil.AUTHORIZATION_KEY,
            value = AuthDataUtil.getAuthorizationValue(token)
        )
    }
}