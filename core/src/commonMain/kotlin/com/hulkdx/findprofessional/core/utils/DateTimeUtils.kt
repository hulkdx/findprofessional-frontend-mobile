package com.hulkdx.findprofessional.core.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn
import kotlinx.datetime.until


fun DayOfWeek.toShort(): String {
    return when (this) {
        DayOfWeek.MONDAY -> "Mon"
        DayOfWeek.TUESDAY -> "Tue"
        DayOfWeek.WEDNESDAY -> "Wed"
        DayOfWeek.THURSDAY -> "Thu"
        DayOfWeek.FRIDAY -> "Fri"
        DayOfWeek.SATURDAY -> "Sat"
        DayOfWeek.SUNDAY -> "Sun"
        else -> throw RuntimeException("Invalid")
    }
}

// From: https://github.com/Kotlin/kotlinx-datetime/issues/184
fun LocalDate.lengthOfMonth(): Int {
    val start = LocalDate(year, month, 1)
    val end = start.plus(1, DateTimeUnit.MONTH)
    return start.until(end, DateTimeUnit.DAY)
}

fun LocalDateTime.lengthOfMonth(): Int {
    val start = LocalDate(year, month, 1)
    val end = start.plus(1, DateTimeUnit.MONTH)
    return start.until(end, DateTimeUnit.DAY)
}

fun LocalDate.Companion.now(): LocalDate {
    return Clock.System.todayIn(TimeZone.UTC)
}

fun LocalDate.Companion.now(clockProvider: ClockProvider): LocalDate {
    return clockProvider.clock().todayIn(TimeZone.UTC)
}

fun LocalDate.shortDayOfWeeks(): String {
    return dayOfWeek.name.capitalize()
        .take(3)
}

private fun String.capitalize(): String {
    return lowercase()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
}
