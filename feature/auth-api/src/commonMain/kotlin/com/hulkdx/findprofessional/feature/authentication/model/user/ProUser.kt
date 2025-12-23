package com.hulkdx.findprofessional.feature.authentication.model.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("pro")
data class ProUser(
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val coachType: String = "",
    val priceNumber: Long? = null,
    val priceCurrency: String? = "",
    val profileImageUrl: String? = "",
    val description: String? = null,
    val skypeId: String? = null,
) : UserType() {

    val fullName: String
        get() = "$firstName $lastName"
}
