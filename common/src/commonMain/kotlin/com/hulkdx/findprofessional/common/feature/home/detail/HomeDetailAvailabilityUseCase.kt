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
}
