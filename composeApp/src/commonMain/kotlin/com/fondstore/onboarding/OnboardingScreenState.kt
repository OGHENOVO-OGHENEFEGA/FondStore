package com.fondstore.onboarding

data class OnboardingScreenState(
    val pages: List<OnboardingScreenPage>,
    val destination: OnboardingScreenDestination? = null
)
