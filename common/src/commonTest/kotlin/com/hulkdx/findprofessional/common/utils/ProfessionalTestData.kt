package com.hulkdx.findprofessional.common.utils

import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalAvailability


fun createProfessional(
    availability: List<ProfessionalAvailability> = listOf(),
) = Professional(
    id = 0,
    email = "",
    firstName = null,
    lastName = null,
    coachType = null,
    priceNumber = null,
    priceCurrency = null,
    profileImageUrl = null,
    rating = null,
    description = null,
    availability = availability,
    reviewSize = "",
    reviews = listOf(),
    createdAt = "",
    updatedAt = ""
)
