package com.hulkdx.findprofessional.core.features.pro.model

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable
import kotlin.time.Instant

/**
 * from 0 time to 0 time means its available the whole day
 */
@Serializable
data class ProfessionalAvailability(
    val id: Long,
    val from: Instant,
    val to: Instant,
    val createdAt: String = "",
    val updatedAt: String = "",
) {
    val date = from.toLocalDateTime(TimeZone.currentSystemDefault()).date
    val fromTime = from.toLocalDateTime(TimeZone.currentSystemDefault()).time
    val toTime = to.toLocalDateTime(TimeZone.currentSystemDefault()).time
}
