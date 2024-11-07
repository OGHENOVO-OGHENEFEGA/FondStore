package com.fondstore.product.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val description: String,
    val id: Int,
    val image: String,
    val name: String
)