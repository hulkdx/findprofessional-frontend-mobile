package com.hulkdx.findprofessional.common.feature.home.detail

import kotlinx.datetime.LocalDate

class HomeDetailAvailabilityUseCase {
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
}
