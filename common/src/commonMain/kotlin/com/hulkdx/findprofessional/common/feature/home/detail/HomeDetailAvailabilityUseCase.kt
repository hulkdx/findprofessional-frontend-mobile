package com.hulkdx.findprofessional.common.feature.home.detail

import kotlinx.datetime.LocalDate

class HomeDetailAvailabilityUseCase {
    fun currentMonth(now: LocalDate): String {
        return ""
        // return now.format(DateTimeFormatter.ofPattern("MMMM yyyy"))
    }
}
