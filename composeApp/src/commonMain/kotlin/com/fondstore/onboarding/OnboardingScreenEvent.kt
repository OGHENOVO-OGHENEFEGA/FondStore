package com.fondstore.onboarding

sealed interface OnboardingScreenEvent {
    data class Navigate(val destination: OnboardingScreenDestination) : OnboardingScreenEvent
}
