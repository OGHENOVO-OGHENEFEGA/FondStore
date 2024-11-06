package com.fondstore.returnAndExchangePolicy.data.mappers

import com.fondstore.returnAndExchangePolicy.data.remote.responses.ReturnAndExchangePolicyResponse
import com.fondstore.returnAndExchangePolicy.domain.errors.ReturnAndExchangePolicyError
import com.fondstore.returnAndExchangePolicy.domain.models.ReturnAndExchangePolicy

fun ReturnAndExchangePolicyResponse.Success.toPolicy(): ReturnAndExchangePolicy {
    return ReturnAndExchangePolicy(policy = policy)
}

fun ReturnAndExchangePolicyResponse.Error.toError(): ReturnAndExchangePolicyError {
    return ReturnAndExchangePolicyError(error = error.ifBlank { detail })
}