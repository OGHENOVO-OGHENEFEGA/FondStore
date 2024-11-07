package com.fondstore.search

import com.fondstore.error.Result
import com.fondstore.product.domain.errors.ProductError
import com.fondstore.product.domain.models.Product

data class SearchScreenState(
    val query: String = "",
    val isSearching: Boolean = false,
    val result: Result<List<Product>, ProductError>? = null,
    val destination: SearchScreenDestination? = null,
    val isActive: Boolean = true
)
