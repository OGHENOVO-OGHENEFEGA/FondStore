package com.fondstore.faqs.presentation

sealed class FaqsScreenEvent {
    data object RefreshScreen : FaqsScreenEvent()
    data object CloseScreen : FaqsScreenEvent()
}