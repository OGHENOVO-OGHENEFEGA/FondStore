package com.fondstore.privacyPolicy.domain.errors

data class PrivacyPolicyError(
    val error: String = "",
    val exception: Exception? = null
)
