package com.fondstore.helpCentre

sealed interface HelpCentreScreenDestination {
    data object FaqsScreen : HelpCentreScreenDestination
    data object PrivacyPolicyScreen : HelpCentreScreenDestination
    data object TermsAndConditionScreen : HelpCentreScreenDestination
    data object ReturnAndExchangePolicyScreen : HelpCentreScreenDestination
    data object ContactUsScreen : HelpCentreScreenDestination
}