package com.hulkdx.findprofessional.common.feature.home

import kotlinx.serialization.Serializable

@Serializable
data class Professional(
    val fullName: String,
    val coachType: String,
    val price: String,
    val description: String,
    val imageUrl: String,
    val rating: String,
)
