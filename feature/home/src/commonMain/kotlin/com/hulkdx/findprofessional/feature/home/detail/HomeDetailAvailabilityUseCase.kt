package com.hulkdx.findprofessional.feature.home.detail

import com.hulkdx.findprofessional.core.model.pro.Professional
import com.hulkdx.findprofessional.core.utils.lengthOfMonth
import com.hulkdx.findprofessional.core.utils.now
import com.hulkdx.findprofessional.core.utils.shortDayOfWeeks
import com.hulkdx.findprofessional.feature.home.detail.model.AvailabilityData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.datetime.DateTimeUnit.Companion.MONTH
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus

class HomeDetailAvailabilityUseCase {
    private val date = MutableStateFlow(LocalDate.now())

    fun getAvailabilityData(professional: Professional): Flow<AvailabilityData> {
        return date
            .map { date ->
                AvailabilityData(
                    currentMonth = currentMonth(date),
                    firstDay = firstDayInt(date),
                    lengthOfMonth = lengthOfMonth(date),
                    now = date,
                    professionalAvailabilityDates = professional.availability.map { it.date },
                )
            }
            .distinctUntilChanged()
    }

    fun monthMinusOne() {
        date.value = date.value.minus(1, MONTH)
    }

    fun monthPlusOne() {
        date.value = date.value.plus(1, MONTH)
    }

    private fun currentMonth(now: LocalDate): String {
        val month = now.month.name.capitalize()
        val year = now.year
        return "$month $year"
    }

    private fun firstDay(now: LocalDate): String {
        val date = LocalDate(now.year, now.monthNumber, 1)
        return date.shortDayOfWeeks()
    }

    private fun firstDayInt(now: LocalDate): Int {
        val str = firstDay(now)
        return weekNumberMap
            .filter { it.value == str }
            .firstNotNullOf { it.key }
    }

    private fun lengthOfMonth(now: LocalDate): Int {
        return now.lengthOfMonth()
    }

    companion object {
        val weekNumberMap = mapOf(
            0 to "Mon",
            1 to "Tue",
            2 to "Wed",
            3 to "Thu",
            4 to "Fri",
            5 to "Sat",
            6 to "Sun",
        )
    }
}

private fun String.capitalize(): String {
    return lowercase()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
}
