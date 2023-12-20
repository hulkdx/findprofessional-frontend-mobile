package com.hulkdx.findprofessional.feature.home.detail.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter


object HomeDetailDateFormatter {
    fun calendarDateFormat(
        now: LocalDate = LocalDate.now(),
    ): String {
        return now.format(DateTimeFormatter.ofPattern("MMMM yyyy"))
    }
}
