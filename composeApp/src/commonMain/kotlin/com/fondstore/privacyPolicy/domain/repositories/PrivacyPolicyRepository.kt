package com.fondstore.privacyPolicy.domain.repositories

import com.fondstore.error.Result
import com.fondstore.privacyPolicy.domain.errors.PrivacyPolicyError
import com.fondstore.privacyPolicy.domain.models.PrivacyPolicy

interface PrivacyPolicyRepository {
    suspend fun getPrivacyPolicy(): Result<PrivacyPolicy?, PrivacyPolicyError>
}