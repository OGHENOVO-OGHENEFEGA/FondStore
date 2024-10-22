package com.fondstore.product.domain.models

import com.fondstore.rating.domain.models.Rating

data class Product(
    val additionalImages: List<String>,
    val averageRating: Float?,
    val category: Int,
    val colours: List<String>,
    val description: String,
    val favoriteId: String?,
    val hasBought: Boolean,
    val id: String,
    val image: String,
    val isFavourite: Boolean,
    val name: String,
    val newPrice: String?,
    val price: String,
    val ratings: List<Rating>,
    val section: Int,
    val sizes: List<Size>,
    val style: String?,
    val subCategory: Int,
    val weight: String
)