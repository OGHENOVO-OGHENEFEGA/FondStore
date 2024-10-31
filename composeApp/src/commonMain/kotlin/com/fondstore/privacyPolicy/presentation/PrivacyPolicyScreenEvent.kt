package com.fondstore.privacyPolicy.presentation

sealed class PrivacyPolicyScreenEvent {
    data object RefreshScreen : PrivacyPolicyScreenEvent()
    data object CloseScreen : PrivacyPolicyScreenEvent()
}