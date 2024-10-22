package com.fondstore.helpCentre

sealed interface HelpCentreScreenEvent {
    data class Navigate(val destination: HelpCentreScreenDestination) : HelpCentreScreenEvent
    data object ClearDestination : HelpCentreScreenEvent
}