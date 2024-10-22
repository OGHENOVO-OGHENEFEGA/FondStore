package com.fondstore.onboarding

sealed interface OnboardingScreenDestination {
    data object StoreScreen : OnboardingScreenDestination
}