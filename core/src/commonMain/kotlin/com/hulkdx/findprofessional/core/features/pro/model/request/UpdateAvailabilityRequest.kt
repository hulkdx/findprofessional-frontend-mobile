package com.hulkdx.findprofessional.core.features.pro.model.request

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class UpdateAvailabilityRequest(
    val items: List<UpdateAvailabilityItemRequest>
)

@Serializable
data class UpdateAvailabilityItemRequest(
    val from: Instant,
    val to: Instant
)
