package com.fondstore.contactUs.data.mappers

import com.fondstore.contactUs.data.remote.responses.HelpCentreResponse
import com.fondstore.contactUs.data.remote.responses.SocialResponse
import com.fondstore.contactUs.domain.errors.HelpCentreError
import com.fondstore.contactUs.domain.errors.SocialError
import com.fondstore.contactUs.domain.models.HelpCentre
import com.fondstore.contactUs.domain.models.Social

fun SocialResponse.Success.toSocial(): Social {
    return Social(icon = icon, name = name, url = url)
}

fun SocialResponse.Error.toError(): SocialError {
    return SocialError(error = error.ifBlank { detail })
}

fun HelpCentreResponse.Success.toHelpCentre(): HelpCentre {
    return HelpCentre(address = address, email = email, phoneNumber = phoneNumber)
}

fun HelpCentreResponse.Error.toError(): HelpCentreError {
    return HelpCentreError(error = error.ifBlank { detail })
}