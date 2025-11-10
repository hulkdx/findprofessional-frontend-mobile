package com.hulkdx.findprofessional.feature.pro.availability.detail

import com.hulkdx.findprofessional.core.utils.TimeUtils
import kotlinx.datetime.LocalTime

data class TimeSlot(
    val from: LocalTime,
    val to: LocalTime,
) {
    val fromDisplayValue = from.displayValue()
    val toDisplayValue = from.displayValue()

    private fun LocalTime.displayValue(): String {
        val minutesOfDay = hour * 60 + minute
        return TimeUtils.formattedTime(minutesOfDay)
    }
}
