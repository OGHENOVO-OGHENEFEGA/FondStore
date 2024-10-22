package profile.data.mapper

import com.fondstore.ProfileEntity
import profile.data.remote.responses.ProfileResponse
import profile.data.remote.responses.UserInfoResponse
import profile.domain.models.Profile
import profile.domain.models.UserInfo

fun UserInfoResponse.toInfo(): UserInfo {
    return UserInfo(id = id, username = username, email = email)
}

fun ProfileEntity.toProfile(): Profile {
    return Profile(
        id = uid.toInt(),
        image = image,
        username = username,
        email = email,
        firstname = firstname,
        lastname = lastname,
        phoneNumber = phoneNumber
    )
}

fun ProfileResponse.toProfile(): Profile {
    return Profile(
        id = id,
        image = image,
        firstname = firstname,
        lastname = lastname,
        phoneNumber = phoneNumber
    )
}