package com.hulkdx.findprofessional.core.model.user

import com.hulkdx.findprofessional.core.utils.PriceUtils
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

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
    @Transient val priceString  = priceNumber?.let { PriceUtils.toPriceString(priceNumber) } ?: ""

    val fullName: String
        get() = "$firstName $lastName"
}
