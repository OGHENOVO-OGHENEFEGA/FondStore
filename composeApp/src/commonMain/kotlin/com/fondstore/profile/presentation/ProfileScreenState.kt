package com.fondstore.profile.presentation

import com.fondstore.error.domain.models.Result
import com.fondstore.profile.domain.errors.ProfileError
import com.fondstore.profile.domain.models.Profile

data class ProfileScreenState(
    val isGettingProfile: Boolean = false,
    val isUpdatingProfile: Boolean = false,
    val result: Result<Profile?, ProfileError>? = null,
    val isInEditMode: Boolean = false,
    val firstname: String = "",
    val lastname: String = "",
    val phoneNumber: String = "",
    val destination: ProfileScreenDestination? = null,
    val isActive: Boolean = true
)
