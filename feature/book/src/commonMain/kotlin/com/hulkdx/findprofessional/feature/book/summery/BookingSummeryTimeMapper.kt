package com.hulkdx.findprofessional.feature.book.summery

import androidx.annotation.VisibleForTesting
import com.hulkdx.findprofessional.core.features.book.SelectedTimes
import com.hulkdx.findprofessional.core.features.pro.Professional
import com.hulkdx.findprofessional.core.utils.CurrencyFormatter
import com.hulkdx.findprofessional.core.utils.NumberFormatter
import com.hulkdx.findprofessional.core.utils.shortDayOfWeeks
import com.hulkdx.findprofessional.feature.book.time.utils.BookingTimeUtils.currentDay
import com.hulkdx.findprofessional.core.utils.TimeUtils.formattedTime
import kotlinx.datetime.LocalDate

class BookingSummeryTimeMapper {
    fun map(selectedTimes: SelectedTimes) = selectedTimes.items
        .flatMap { (key, times) ->
            times.map { time -> map(key, time) }
        }

    @VisibleForTesting
    internal fun map(date: LocalDate, startTimeMinutes: Int): BookingSummeryUiState.Time {
        val endTime = startTimeMinutes + 30
        val duration = "${formattedTime(startTimeMinutes)} - ${formattedTime(endTime)}"

        return BookingSummeryUiState.Time(
            duration = duration,
            date = currentDay(date),
            day = date.shortDayOfWeeks(),
        )
    }

    @VisibleForTesting
    internal fun calculateTotalPrices(
        professional: Professional,
        sizeOfItemsSelected: Int,
    ): String {
        val cents = professional.priceNumber
        val currency = CurrencyFormatter.toSymbol(professional.priceCurrency)
        val total = cents * sizeOfItemsSelected
        val a = (total / 100)
        val b = NumberFormatter.twoDigits(total % 100)
        return "${a}.${b} $currency"
    }
}
