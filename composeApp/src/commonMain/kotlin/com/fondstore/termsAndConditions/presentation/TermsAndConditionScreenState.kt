package com.fondstore.termsAndConditions.presentation

import com.fondstore.error.Result
import com.fondstore.termsAndConditions.domain.errors.TermsAndConditionError
import com.fondstore.termsAndConditions.domain.models.TermsAndCondition

data class TermsAndConditionScreenState(
    val isRefreshingScreen: Boolean = false,
    val isGettingTermsAndCondition: Boolean = false,
    val result: Result<TermsAndCondition?, TermsAndConditionError>? = null,
    val isActive: Boolean = true
)
