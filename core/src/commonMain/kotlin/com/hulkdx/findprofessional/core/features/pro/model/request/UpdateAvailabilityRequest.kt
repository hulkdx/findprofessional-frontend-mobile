package com.hulkdx.findprofessional.core.features.pro.model.request

import kotlinx.serialization.Serializable

@Serializable
data class UpdateAvailabilityRequest(
    val items: List<UpdateAvailabilityItemRequest>
)

@Serializable
data class UpdateAvailabilityItemRequest(
    val date: String,
    val from: String,
    val to: String
)
