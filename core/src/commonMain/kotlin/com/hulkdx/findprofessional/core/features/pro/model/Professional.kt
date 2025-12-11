package com.hulkdx.findprofessional.core.features.pro.model

import kotlinx.serialization.Serializable

@Serializable
data class Professional(
    val id: Long,
    val email: String,
    val firstName: String? = null,
    val lastName: String? = null,
    val coachType: String? = null,
    val priceNumber: Long = 0,
    val priceCurrency: String = "",
    val profileImageUrl: String? = null,
    val rating: String? = null,
    val description: String? = null,
    val availability: List<ProfessionalAvailability> = listOf(),
    val reviewSize: String,
    val reviews: List<ProfessionalReview> = listOf(),
    val createdAt: String = "",
    val updatedAt: String = "",
) {

    val fullName: String
        get() = "$firstName $lastName"

    val price: String
        get() = "$priceCurrency $priceNumber"

    // TODO:
    val isFav = false
}
