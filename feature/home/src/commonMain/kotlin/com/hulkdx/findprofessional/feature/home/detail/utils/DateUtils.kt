package com.hulkdx.findprofessional.feature.home.detail.utils

import com.hulkdx.findprofessional.core.utils.lengthOfMonth
import com.hulkdx.findprofessional.core.utils.shortDayOfWeeks
import com.hulkdx.findprofessional.feature.home.detail.HomeDetailAvailabilityUseCase.Companion.weekNumberMap
import com.hulkdx.findprofessional.feature.home.detail.capitalize
import kotlinx.datetime.LocalDate


object DateUtils {
    fun currentMonth(now: LocalDate): String {
        val month = now.month.name.capitalize()
        val year = now.year
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
