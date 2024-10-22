package com.fondstore.rating.data.mappers

import com.fondstore.core.domain.utils.formatDateTime
import com.fondstore.core.domain.utils.toLocalDateTime
import com.fondstore.rating.data.remote.responses.RatingResponse
import com.fondstore.rating.data.remote.responses.SuccessfulRatingResponse
import com.fondstore.rating.domain.models.Rating
import com.fondstore.rating.domain.models.SuccessfulRating


fun RatingResponse.toRating(): Rating {
    return Rating(
        createdAt = createdAt.toLocalDateTime().formatDateTime(),
        description = description,
        rating = rating,
        username = username
    )
}

fun SuccessfulRatingResponse.toSuccessfulRating(): SuccessfulRating {
    return SuccessfulRating(description = description, id = id, rating = rating)
}