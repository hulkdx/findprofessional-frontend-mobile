package com.hulkdx.findprofessional.feature.home.detail.utils

import android.os.Parcelable
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.feature.home.detail.utils.HomeDetailDateFormatter.calendarDateFormat
import com.hulkdx.findprofessional.feature.home.detail.utils.HomeDetailDateFormatter.firstDayInt
import com.hulkdx.findprofessional.feature.home.detail.utils.HomeDetailDateFormatter.lengthOfMonth
import kotlinx.datetime.toJavaLocalDate
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class AvailabilityData(
    val calendarDateFormat: String,
    val firstDay: Int,
    val lengthOfMonth: Int,
    val now: Long,
    val professionalAvailabilityDates: List<LocalDate>,
) : Parcelable {

    @IgnoredOnParcel
    val nowLocalDate: LocalDate = LocalDate.ofEpochDay(now)

    fun isSelectedDay(day: Int): Boolean {
        val checkDay = LocalDate.of(nowLocalDate.year, nowLocalDate.month, day)
        return professionalAvailabilityDates.contains(checkDay)
    }
}

fun createAvailabilityData(
    professional: Professional,
    now: LocalDate = LocalDate.now(),
) = AvailabilityData(
    calendarDateFormat = calendarDateFormat(now),
    firstDay = firstDayInt(now),
    lengthOfMonth = lengthOfMonth(now),
    now = now.toEpochDay(),
    professionalAvailabilityDates = professional.availability.map { it.date.toJavaLocalDate() },
)
