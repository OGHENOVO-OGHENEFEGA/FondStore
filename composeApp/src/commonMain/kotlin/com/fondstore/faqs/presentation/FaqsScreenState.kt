package com.fondstore.faqs.presentation

import com.fondstore.error.domain.models.Result
import com.fondstore.faqs.domain.errors.FaqsError
import com.fondstore.faqs.domain.models.Faq

data class FaqsScreenState(
    val isRefreshingScreen: Boolean = false,
    val isGettingFaqs: Boolean = false,
    val result: Result<List<Faq>, FaqsError>? = null,
    val isActive: Boolean = true
)
