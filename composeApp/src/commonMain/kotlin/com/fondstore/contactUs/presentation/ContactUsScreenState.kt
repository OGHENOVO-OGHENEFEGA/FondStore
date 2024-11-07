package com.fondstore.contactUs.presentation

import com.fondstore.contactUs.domain.errors.HelpCentreError
import com.fondstore.contactUs.domain.errors.SocialError
import com.fondstore.contactUs.domain.models.HelpCentre
import com.fondstore.contactUs.domain.models.Social
import com.fondstore.error.Result

data class ContactUsScreenState(
    val isRefreshingScreen: Boolean = false,
    val isGettingContactOptions: Boolean = false,
    val socialsResult: Result<List<Social>, SocialError>? = null,
    val helpCentreResult: Result<HelpCentre?, HelpCentreError>? = null,
    val isActive: Boolean = true
)
