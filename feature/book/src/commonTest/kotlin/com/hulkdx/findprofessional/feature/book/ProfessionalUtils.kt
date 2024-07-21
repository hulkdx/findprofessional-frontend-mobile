package com.hulkdx.findprofessional.feature.book

import com.hulkdx.findprofessional.core.model.pro.Professional
import com.hulkdx.findprofessional.core.model.pro.ProfessionalAvailability

fun createProfessional(
    availability: List<ProfessionalAvailability> = listOf(),
    priceNumber: Int = 0,
    priceCurrency: String = "",
) = Professional(
    id = 0,
    email = "",
    firstName = null,
    lastName = null,
    coachType = null,
    priceNumber = priceNumber,
    priceCurrency = priceCurrency,
    profileImageUrl = null,
    rating = null,
    description = null,
    availability = availability,
    reviewSize = "",
    reviews = listOf(),
    createdAt = "",
    updatedAt = ""
)
