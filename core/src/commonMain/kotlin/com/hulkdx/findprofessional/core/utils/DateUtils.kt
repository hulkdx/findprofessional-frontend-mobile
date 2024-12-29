package com.hulkdx.findprofessional.core.utils

import kotlinx.datetime.LocalDate

object DateUtils {
    val weekNumberMap = mapOf(
        0 to "Mon",
        1 to "Tue",
        2 to "Wed",
        3 to "Thu",
        4 to "Fri",
        5 to "Sat",
        6 to "Sun",
    )

    fun formatToMonthsAndYear(time: LocalDate): String {
        val month = time.month.name
            .lowercase()
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        val year = time.year
        return "$month $year"
    }

    fun firstDay(now: LocalDate): String {
        val date = LocalDate(now.year, now.monthNumber, 1)
        return date.shortDayOfWeeks()
    }

    fun firstDayInt(now: LocalDate): Int {
        val str = firstDay(now)
        return weekNumberMap
            .filter { it.value == str }
            .firstNotNullOf { it.key }
    }

    fun lengthOfMonth(now: LocalDate): Int {
        return now.lengthOfMonth()
    }

}
