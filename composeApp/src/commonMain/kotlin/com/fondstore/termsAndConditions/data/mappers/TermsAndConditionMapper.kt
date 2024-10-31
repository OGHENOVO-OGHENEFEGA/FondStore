package com.fondstore.termsAndConditions.data.mappers

import com.fondstore.termsAndConditions.data.remote.responses.TermsAndConditionResponse
import com.fondstore.termsAndConditions.domain.errors.TermsAndConditionError
import com.fondstore.termsAndConditions.domain.models.TermsAndCondition

fun TermsAndConditionResponse.Success.toTermsAndCondition(): TermsAndCondition {
    return TermsAndCondition(termsAndCondition = termsAndCondition)
}

fun TermsAndConditionResponse.Error.toError(): TermsAndConditionError {
    return TermsAndConditionError(error = error.ifBlank { detail })
}