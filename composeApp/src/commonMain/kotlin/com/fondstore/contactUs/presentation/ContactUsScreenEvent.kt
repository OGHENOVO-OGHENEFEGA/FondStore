package com.fondstore.contactUs.presentation

sealed class ContactUsScreenEvent {
    data object RefreshScreen : ContactUsScreenEvent()
    data object OpenEmail : ContactUsScreenEvent()
    data object OpenPhone : ContactUsScreenEvent()
    data object OpenAddress : ContactUsScreenEvent()
    data class OpenUrl(val url: String) : ContactUsScreenEvent()
    data object CloseScreen : ContactUsScreenEvent()
}