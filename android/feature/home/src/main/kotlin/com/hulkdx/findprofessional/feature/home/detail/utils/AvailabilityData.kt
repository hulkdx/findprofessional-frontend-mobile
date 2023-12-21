package com.hulkdx.findprofessional.feature.home.detail.utils

import com.hulkdx.findprofessional.feature.home.detail.utils.HomeDetailDateFormatter.calendarDateFormat
import com.hulkdx.findprofessional.feature.home.detail.utils.HomeDetailDateFormatter.firstDayInt
import com.hulkdx.findprofessional.feature.home.detail.utils.HomeDetailDateFormatter.lengthOfMonth


data class AvailabilityData(
    val calendarDateFormat: String,
    val firstDay: Int,
    val lengthOfMonth: Int,
)

fun createAvailabilityData() = AvailabilityData(
    calendarDateFormat = calendarDateFormat(),
    firstDay = firstDayInt(),
    lengthOfMonth = lengthOfMonth(),
)
