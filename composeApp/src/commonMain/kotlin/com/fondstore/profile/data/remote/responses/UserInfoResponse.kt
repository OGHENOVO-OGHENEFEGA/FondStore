package profile.data.remote.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponse(
    @SerialName("email")
    val email: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("username")
    val username: String = ""
)