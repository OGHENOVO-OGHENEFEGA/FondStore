package com.fondstore.termsAndConditions.domain.repositories

import com.fondstore.error.Result
import com.fondstore.termsAndConditions.domain.errors.TermsAndConditionError
import com.fondstore.termsAndConditions.domain.models.TermsAndCondition

interface TermsAndConditionRepository {
    suspend fun getTermsAndCondition(): Result<TermsAndCondition?, TermsAndConditionError>
}