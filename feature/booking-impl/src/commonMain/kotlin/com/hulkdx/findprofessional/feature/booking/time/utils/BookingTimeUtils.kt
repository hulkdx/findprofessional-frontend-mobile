package com.hulkdx.findprofessional.feature.booking.time.utils

import com.hulkdx.findprofessional.feature.pro.model.ProfessionalAvailability
import com.hulkdx.findprofessional.core.utils.toMinutesOfDay
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.number

object BookingTimeUtils {

    fun isAvailabilityIncludedInTimes(
        availability: ProfessionalAvailability,
        from: Int,
        to: Int,
        timeZone: TimeZone = TimeZone.currentSystemDefault(),
    ): Boolean {
        val availabilityFrom = availability.from.toMinutesOfDay(timeZone)
        val availabilityTo = availability.to.toMinutesOfDay(timeZone).let { aT ->
            if (aT == 0) {
                24 * 60
            } else {
                aT
            }
        }

        return availabilityFrom <= from &&
                availabilityTo >= to
    }

    fun currentDay(now: LocalDate): String {
        return "${now.dayOfMonth}.${now.month.number}.${now.year}"
    }
}
