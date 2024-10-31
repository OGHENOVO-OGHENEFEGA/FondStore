package com.fondstore.faqs.domain.repositories

import com.fondstore.error.domain.models.Result
import com.fondstore.faqs.domain.errors.FaqsError
import com.fondstore.faqs.domain.models.Faq

interface FaqsRepository {
    suspend fun getFaqs(): Result<List<Faq>, FaqsError>
}