package com.hulkdx.findprofessional.feature.home.detail.utils

import com.hulkdx.findprofessional.feature.home.detail.utils.HomeDetailDateFormatter.calendarDateFormat
import com.hulkdx.findprofessional.feature.home.detail.utils.HomeDetailDateFormatter.firstDayInt
import com.hulkdx.findprofessional.feature.home.detail.utils.HomeDetailDateFormatter.lengthOfMonth


data class HomeDetailDateDataHolder(
    val calendarDateFormat: String,
    val firstDay: Int,
    val lengthOfMonth: Int,
)

fun createHomeDetailDate() = HomeDetailDateDataHolder(
    calendarDateFormat = calendarDateFormat(),
    firstDay = firstDayInt(),
    lengthOfMonth = lengthOfMonth(),
)
