package com.fondstore.contactUs.domain.repositories

import com.fondstore.contactUs.domain.errors.HelpCentreError
import com.fondstore.contactUs.domain.errors.SocialError
import com.fondstore.contactUs.domain.models.HelpCentre
import com.fondstore.contactUs.domain.models.Social
import com.fondstore.error.Result

interface ContactUsRepository {
    suspend fun getSocials(): Result<List<Social>, SocialError>
    suspend fun getHelpCentre(): Result<HelpCentre?, HelpCentreError>
}