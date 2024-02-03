package com.hulkdx.findprofessional.common.feature.book.time

import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalAvailability
import com.hulkdx.findprofessional.common.utils.toMinutesOfDay
import kotlinx.datetime.LocalDate
import kotlinx.datetime.number

object BookingTimeUtils {

    fun isAvailabilityIncludedInTimes(
        availability: ProfessionalAvailability,
        from: Int,
        to: Int,
    ): Boolean {
        val availabilityFrom = availability.from.toMinutesOfDay()
        val availabilityTo = availability.to.toMinutesOfDay().let { aT ->
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
