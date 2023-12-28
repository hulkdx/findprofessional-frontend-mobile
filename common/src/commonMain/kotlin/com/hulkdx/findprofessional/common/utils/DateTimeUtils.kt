package com.hulkdx.findprofessional.common.utils

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.DayOfWeek.FRIDAY
import kotlinx.datetime.DayOfWeek.MONDAY
import kotlinx.datetime.DayOfWeek.SATURDAY
import kotlinx.datetime.DayOfWeek.SUNDAY
import kotlinx.datetime.DayOfWeek.THURSDAY
import kotlinx.datetime.DayOfWeek.TUESDAY
import kotlinx.datetime.DayOfWeek.WEDNESDAY
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import kotlinx.datetime.until


fun DayOfWeek.toShort(): String {
    return when (this) {
        MONDAY -> "Mon"
        TUESDAY -> "Tue"
        WEDNESDAY -> "Wed"
        THURSDAY -> "Thu"
        FRIDAY -> "Fri"
        SATURDAY -> "Sat"
        SUNDAY -> "Sun"
        else -> throw RuntimeException("Invalid")
    }
}

// From: https://github.com/Kotlin/kotlinx-datetime/issues/184
fun LocalDate.lengthOfMonth(): Int {
    val start = LocalDate(year, month, 1)
    val end = start.plus(1, DateTimeUnit.MONTH)
    return start.until(end, DateTimeUnit.DAY)
}
