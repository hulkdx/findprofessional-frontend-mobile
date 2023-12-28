package com.hulkdx.findprofessional.feature.home.detail.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter


object HomeDetailDateFormatter {
    val weekNumberMap = mapOf(
        0 to "Mon",
        1 to "Tue",
        2 to "Wed",
        3 to "Thu",
        4 to "Fri",
        5 to "Sat",
        6 to "Sun",
    )

    fun currentMonth(now: LocalDate = LocalDate.now()): String {
        return now.format(DateTimeFormatter.ofPattern("MMMM yyyy"))
    }

    fun firstDay(now: LocalDate = LocalDate.now()): String? {
        return LocalDate.of(now.year, now.monthValue, 1)
            .format(DateTimeFormatter.ofPattern("E"))
    }

    fun firstDayInt(now: LocalDate = LocalDate.now()): Int {
        val str = firstDay(now)
        return weekNumberMap
            .filter { it.value == str }
            .firstNotNullOf { it.key }
    }

    fun lengthOfMonth(now: LocalDate = LocalDate.now()): Int {
        return now.lengthOfMonth()
    }
}
