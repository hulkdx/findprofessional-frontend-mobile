package com.hulkdx.findprofessional.common.utils

import com.hulkdx.findprofessional.common.feature.home.model.Professional


fun createProfessional() = Professional(
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
    availability = listOf(),
    reviewSize = "",
    reviews = listOf(),
    createdAt = "",
    updatedAt = ""
)
