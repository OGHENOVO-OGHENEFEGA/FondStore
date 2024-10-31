package com.fondstore.faqs.data.mappers

import com.fondstore.faqs.domain.models.Faq
import com.fondstore.faqs.data.remote.responses.FaqResponse
import com.fondstore.faqs.domain.errors.FaqsError

fun FaqResponse.Success.toFaq(): Faq {
    return Faq(answer = answer, question = question)
}

fun FaqResponse.Error.toError(): FaqsError {
    return FaqsError(error.ifBlank { detail })
}