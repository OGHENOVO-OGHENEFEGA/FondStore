package com.fondstore.termsAndConditions.presentation

sealed class TermsAndConditionScreenEvent {
    data object RefreshScreen : TermsAndConditionScreenEvent()
    data object CloseScreen : TermsAndConditionScreenEvent()
}