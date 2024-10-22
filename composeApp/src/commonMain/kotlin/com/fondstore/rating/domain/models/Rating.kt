package com.fondstore.rating.domain.models

data class Rating(
    val createdAt: String,
    val description: String,
    val rating: Float,
    val username: String
)