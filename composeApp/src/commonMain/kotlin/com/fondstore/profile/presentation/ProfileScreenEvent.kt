package com.fondstore.profile.presentation

sealed interface ProfileScreenEvent {
    data object ToggleEditMode : ProfileScreenEvent

    data class SetFirstName(val name: String) : ProfileScreenEvent
    data class SetLastName(val name: String) : ProfileScreenEvent
    data class SetPhoneNumber(val number: String) : ProfileScreenEvent

    data object UploadProfile : ProfileScreenEvent

    data object ShowSignOutDialog : ProfileScreenEvent

    data class Navigate(val destination: ProfileScreenDestination) : ProfileScreenEvent
    data object ClearDestination : ProfileScreenEvent
}