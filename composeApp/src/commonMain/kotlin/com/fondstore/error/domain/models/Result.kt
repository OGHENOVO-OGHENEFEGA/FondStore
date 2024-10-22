package com.fondstore.error.domain.models

sealed interface Result<D, E> {
    data class Success<D, E>(val data: D) : Result<D, E>
    data class Error<D, E>(val error: E) : Result<D, E>
    data class Exception<D, E>(val exception: kotlin.Exception) : Result<D, E>

    val dataOrNull get() = if (this is Success) data else null
    val errorOrNull get() = if (this is Error) error else null
    val exceptionOrNull get() = if (this is Exception) exception else null
}