package com.fondstore.returnAndExchangePolicy.data.remote.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface ReturnAndExchangePolicyResponse {
    @Serializable
    data class Success(
        @SerialName("return_and_exchange_policy")
        val policy: String = "",
    ) : ReturnAndExchangePolicyResponse

    @Serializable
    data class Error(
        @SerialName("detail")
        val detail: String = "",
        @SerialName("error")
        val error: String = "",
    ) : ReturnAndExchangePolicyResponse
}