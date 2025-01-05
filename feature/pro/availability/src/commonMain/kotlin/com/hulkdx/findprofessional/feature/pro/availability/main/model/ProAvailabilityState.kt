package com.hulkdx.findprofessional.feature.pro.availability.main.model

import kotlin.math.ceil

data class ProAvailabilityState(
    val currentMonth: String,
    val firstDay: Int,
    val lengthOfMonth: Int,
) {
    val perWeek = ceil((lengthOfMonth + firstDay) / 7F).toInt()
}
