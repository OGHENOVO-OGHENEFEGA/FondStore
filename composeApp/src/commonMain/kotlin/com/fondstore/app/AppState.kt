package com.fondstore.app

import com.fondstore.error.domain.models.Result
import com.fondstore.profile.domain.errors.ProfileError
import com.fondstore.profile.domain.models.Profile

data class AppState(
    val profileResult: Result<Profile?, ProfileError>? = null,
)
