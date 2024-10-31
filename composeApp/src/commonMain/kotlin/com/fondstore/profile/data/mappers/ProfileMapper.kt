package com.fondstore.profile.data.mappers

import com.fondstore.profile.data.local.ProfileObject
import com.fondstore.profile.data.remote.responses.ProfileResponse
import com.fondstore.profile.data.remote.responses.UserInfoResponse
import com.fondstore.profile.domain.errors.ProfileError
import com.fondstore.profile.domain.errors.UserInfoError
import com.fondstore.profile.domain.models.Profile
import com.fondstore.profile.domain.models.UserInfo
import io.realm.kotlin.notifications.ResultsChange

fun ResultsChange<ProfileObject>.toProfile(): Profile? {
    return list.firstOrNull()?.let {
        Profile(
            id = it.id,
            image = it.image,
            username = it.username,
            email = it.email,
            firstname = it.firstname,
            lastname = it.lastname,
            phoneNumber = it.phoneNumber
        )
    }
}

fun UserInfoResponse.Success.toInfo(): UserInfo {
    return UserInfo(id = id, username = username, email = email)
}

fun UserInfoResponse.Error.toError(): UserInfoError {
    return UserInfoError(error = error.ifBlank { detail })
}

fun ProfileResponse.Success.toProfile(username: String, email: String): Profile {
    return Profile(
        id = id,
        image = image,
        username = username,
        email = email,
        firstname = firstname,
        lastname = lastname,
        phoneNumber = phoneNumber
    )
}

fun ProfileResponse.Error.toError(): ProfileError {
    return ProfileError(error = error.ifBlank { detail })
}