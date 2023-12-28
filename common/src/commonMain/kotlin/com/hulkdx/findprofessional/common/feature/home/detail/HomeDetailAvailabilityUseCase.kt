package com.hulkdx.findprofessional.common.feature.home.detail

import com.hulkdx.findprofessional.common.utils.lengthOfMonth
import kotlinx.datetime.LocalDate

class HomeDetailAvailabilityUseCase {
    val weekNumberMap = mapOf(
        0 to "Mon",
        1 to "Tue",
        2 to "Wed",
        3 to "Thu",
        4 to "Fri",
        5 to "Sat",
        6 to "Sun",
    )

    fun currentMonth(now: LocalDate): String {
        val month = now.month.name
            .lowercase()
            // capitalize
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        val year = now.year
        return "$month $year"
    }

    fun firstDay(now: LocalDate): String? {
        val date = LocalDate(now.year, now.monthNumber, 1)
        return date
            .dayOfWeek.name
            .lowercase()
            // capitalize
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
            .take(3)
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
