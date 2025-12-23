package com.hulkdx.findprofessional.feature.booking.summery

import androidx.annotation.VisibleForTesting
import com.hulkdx.findprofessional.core.features.pro.model.ProfessionalAvailability
import com.hulkdx.findprofessional.core.utils.CurrencyFormatter
import com.hulkdx.findprofessional.core.utils.TimeUtils.formattedTime
import com.hulkdx.findprofessional.core.utils.shortDayOfWeeks
import com.hulkdx.findprofessional.core.utils.toMinutesOfDay
import com.hulkdx.findprofessional.feature.booking.summery.BookingSummeryUiState.SummeryDetails
import com.hulkdx.findprofessional.feature.booking.time.utils.BookingTimeUtils.currentDay
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class BookingSummeryTimeMapper {
    fun map(
        availabilities: List<ProfessionalAvailability>,
        timeZone: TimeZone = TimeZone.currentSystemDefault()
    ): List<SummeryDetails.Time> {
        return availabilities
            .sortedWith(compareBy({ it.from }))
            .map { availability ->
                val localDateTime = availability.from.toLocalDateTime(timeZone)
                map(
                    localDateTime.date,
                    localDateTime.time.toMinutesOfDay(),
                )
            }
    }

    @VisibleForTesting
    internal fun map(date: LocalDate, startTimeMinutes: Int): SummeryDetails.Time {
        val endTime = startTimeMinutes + 30
        val duration = "${formattedTime(startTimeMinutes)} - ${formattedTime(endTime)}"

        return SummeryDetails.Time(
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
