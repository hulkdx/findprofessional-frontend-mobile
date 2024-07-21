package com.hulkdx.findprofessional.core.model.pro

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.serialization.Serializable

/**
 * from 0 time to 0 time means its available the whole day
 */
@Serializable
data class ProfessionalAvailability(
    val date: LocalDate,
    val from: LocalTime,
    val to: LocalTime,
    val createdAt: String = "",
    val updatedAt: String = "",
)
