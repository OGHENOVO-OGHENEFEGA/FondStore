package profile.data.remote.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("image")
    val image: String = "",
    @SerialName("first_name")
    val firstname: String = "",
    @SerialName("last_name")
    val lastname: String = "",
    @SerialName("phone_number")
    val phoneNumber: String = "",
    @SerialName("referral_code")
    val referralCode: String? = null
)