package com.hulkdx.findprofessional.common.feature.book.summery

import com.hulkdx.findprofessional.common.feature.book.time.BookingTimeUtils.currentDay
import com.hulkdx.findprofessional.common.feature.book.time.BookingTimeUtils.formattedTime
import com.hulkdx.findprofessional.common.feature.book.time.SelectedTimes
import com.hulkdx.findprofessional.common.utils.shortDayOfWeeks
import kotlinx.datetime.LocalDate

class BookingSummeryTimeMapper {
    fun map(selectedTimes: SelectedTimes) = selectedTimes.items
        .flatMap { (key, times) ->
            times.map { time -> map(key, time) }
        }

    fun map(date: LocalDate, startTimeMinutes: Int): BookingSummeryUiState.Time {
        val endTime = startTimeMinutes + 30
        val duration = "${formattedTime(startTimeMinutes)} - ${formattedTime(endTime)}"

        return BookingSummeryUiState.Time(
            duration = duration,
            date = currentDay(date),
            day = date.shortDayOfWeeks(),
        )
    }
}
