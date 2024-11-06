package com.fondstore.returnAndExchangePolicy.presentation

import com.fondstore.error.domain.models.Result
import com.fondstore.returnAndExchangePolicy.domain.errors.ReturnAndExchangePolicyError
import com.fondstore.returnAndExchangePolicy.domain.models.ReturnAndExchangePolicy

data class ReturnAndExchangePolicyScreenState(
    val isRefreshingScreen: Boolean = false,
    val isGettingPolicy: Boolean = false,
    val result: Result<ReturnAndExchangePolicy?, ReturnAndExchangePolicyError>? = null,
    val isActive: Boolean = true
)
