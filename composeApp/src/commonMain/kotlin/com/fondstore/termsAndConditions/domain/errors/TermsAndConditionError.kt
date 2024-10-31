package com.fondstore.termsAndConditions.domain.errors

data class TermsAndConditionError(
    val error: String = "",
    val exception: Exception? = null
)
