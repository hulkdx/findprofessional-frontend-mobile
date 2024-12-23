package com.hulkdx.findprofessional.feature.pro.availability.components

import kotlin.math.ceil

data class AvailabilityState(
    val currentMonth: String,
    val firstDay: Int,
    val lengthOfMonth: Int,
//    val now: LocalDate = LocalDate.now(),
//    val professionalAvailabilityDates: List<LocalDate>,
) {
    val perWeek = ceil((lengthOfMonth + firstDay) / 7F).toInt()
}
