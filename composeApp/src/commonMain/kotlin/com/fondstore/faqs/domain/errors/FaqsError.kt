package com.fondstore.faqs.domain.errors

data class FaqsError(
    val error: String = "",
    val exception: Exception? = null
)
