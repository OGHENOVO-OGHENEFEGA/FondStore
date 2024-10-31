package com.fondstore.privacyPolicy.presentation

import com.fondstore.error.domain.models.Result
import com.fondstore.privacyPolicy.domain.errors.PrivacyPolicyError
import com.fondstore.privacyPolicy.domain.models.PrivacyPolicy

data class PrivacyPolicyScreenState(
    val isRefreshingScreen: Boolean = false,
    val isGettingPolicy: Boolean = false,
    val result: Result<PrivacyPolicy?, PrivacyPolicyError>? = null,
    val isActive: Boolean = true
)
