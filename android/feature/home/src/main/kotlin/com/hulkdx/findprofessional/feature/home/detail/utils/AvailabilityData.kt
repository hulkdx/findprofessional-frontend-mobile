package com.hulkdx.findprofessional.feature.home.detail.utils

import android.os.Parcelable
import com.hulkdx.findprofessional.feature.home.detail.utils.HomeDetailDateFormatter.calendarDateFormat
import com.hulkdx.findprofessional.feature.home.detail.utils.HomeDetailDateFormatter.firstDayInt
import com.hulkdx.findprofessional.feature.home.detail.utils.HomeDetailDateFormatter.lengthOfMonth
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class AvailabilityData(
    val calendarDateFormat: String,
    val firstDay: Int,
    val lengthOfMonth: Int,
    val now: Long,
) : Parcelable {

    @IgnoredOnParcel
    val nowLocalDate: LocalDate = LocalDate.ofEpochDay(now)

    fun isSelectedDay(day: Int): Boolean {
        return false
    }
}

fun createAvailabilityData(now: LocalDate = LocalDate.now()) = AvailabilityData(
    calendarDateFormat = calendarDateFormat(now),
    firstDay = firstDayInt(now),
    lengthOfMonth = lengthOfMonth(now),
    now = now.toEpochDay(),
)

fun createAvailabilityDataMonthMinusOne(current: AvailabilityData): AvailabilityData {
    val localDate = current.nowLocalDate
        .minusMonths(1)
    return createAvailabilityData(localDate)
}

fun createAvailabilityDataMonthPlusOne(current: AvailabilityData): AvailabilityData {
    val localDate = current.nowLocalDate
        .plusMonths(1)
    return createAvailabilityData(localDate)
}
