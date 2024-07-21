package com.hulkdx.findprofessional.feature.home.detail.model

import kotlinx.datetime.LocalDate
import kotlin.math.ceil

data class AvailabilityData(
    val currentMonth: String,
    val firstDay: Int,
    val lengthOfMonth: Int,
    val now: LocalDate,
    val professionalAvailabilityDates: List<LocalDate>,
) {

    val perWeek = ceil((lengthOfMonth + firstDay) / 7F).toInt()

    fun isSelectedDay(day: Int): Boolean {
        val checkDay = LocalDate(now.year, now.month, day)
        return professionalAvailabilityDates.contains(checkDay)
    }
}
