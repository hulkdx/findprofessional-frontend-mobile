package com.hulkdx.findprofessional.feature.book.summery

import androidx.annotation.VisibleForTesting
import com.hulkdx.findprofessional.core.features.book.SelectedTimes
import com.hulkdx.findprofessional.core.utils.CurrencyFormatter
import com.hulkdx.findprofessional.core.utils.TimeUtils.formattedTime
import com.hulkdx.findprofessional.core.utils.shortDayOfWeeks
import com.hulkdx.findprofessional.feature.book.summery.BookingSummeryUiState.SummeryDetails.Time
import com.hulkdx.findprofessional.feature.book.time.utils.BookingTimeUtils.currentDay
import kotlinx.datetime.LocalDate

class BookingSummeryTimeMapper {
    fun map(selectedTimes: SelectedTimes) = selectedTimes.items
        .flatMap { (key, times) ->
            times.map { time -> map(key, time) }
        }

    @VisibleForTesting
    internal fun map(date: LocalDate, startTimeMinutes: Int): Time {
        val endTime = startTimeMinutes + 30
        val duration = "${formattedTime(startTimeMinutes)} - ${formattedTime(endTime)}"

        return Time(
            duration = duration,
            date = currentDay(date),
            day = date.shortDayOfWeeks(),
        )
    }

    fun formattedTotalPrices(
        amountInCents: Long,
        currency: String,
    ): String {
        val whole = amountInCents / 100
        val fractional = (amountInCents % 100).toString().padStart(2, '0')
        return "$whole.$fractional ${CurrencyFormatter.toSymbol(currency)}"
    }
}
