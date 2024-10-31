package com.fondstore.privacyPolicy.data.mappers

import com.fondstore.privacyPolicy.data.remote.responses.PrivacyPolicyResponse
import com.fondstore.privacyPolicy.domain.errors.PrivacyPolicyError
import com.fondstore.privacyPolicy.domain.models.PrivacyPolicy

fun PrivacyPolicyResponse.Success.toPrivacyPolicy(): PrivacyPolicy {
    return PrivacyPolicy(policy = privacyPolicy)
}

fun PrivacyPolicyResponse.Error.toError(): PrivacyPolicyError {
    return PrivacyPolicyError(error = error.ifBlank { detail })
}