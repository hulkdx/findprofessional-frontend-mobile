package com.hulkdx.findprofessional.core.model.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("pro")
data class ProUser(
    val email: String,
    val firstName: String,
    val lastName: String,
    val coachType: String,
    val priceNumber: Long?,
    val priceCurrency: String?,
    val profileImageUrl: String?,
    val description: String?,
    val skypeId: String?,
) : UserType()
