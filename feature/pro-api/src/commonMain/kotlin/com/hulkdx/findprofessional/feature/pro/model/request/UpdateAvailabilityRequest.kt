package com.hulkdx.findprofessional.feature.pro.model.request

import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class UpdateAvailabilityRequest(
    val items: List<UpdateAvailabilityItemRequest>
)

@Serializable
data class UpdateAvailabilityItemRequest(
    val from: Instant,
    val to: Instant
)
