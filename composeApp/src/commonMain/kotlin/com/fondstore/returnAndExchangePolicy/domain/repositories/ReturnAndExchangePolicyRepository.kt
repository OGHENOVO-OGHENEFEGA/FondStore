package com.fondstore.returnAndExchangePolicy.domain.repositories

import com.fondstore.error.domain.models.Result
import com.fondstore.returnAndExchangePolicy.domain.errors.ReturnAndExchangePolicyError
import com.fondstore.returnAndExchangePolicy.domain.models.ReturnAndExchangePolicy

interface ReturnAndExchangePolicyRepository {
    suspend fun getReturnAndExchangePolicy(): Result<ReturnAndExchangePolicy?, ReturnAndExchangePolicyError>
}