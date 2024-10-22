package com.fondstore.onboarding

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class OnboardingScreenPage(
    val image: DrawableResource,
    val heading: StringResource,
    val text: StringResource
)
