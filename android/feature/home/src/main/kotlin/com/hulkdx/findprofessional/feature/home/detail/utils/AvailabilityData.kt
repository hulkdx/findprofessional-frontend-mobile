package com.hulkdx.findprofessional.feature.home.detail.utils

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class AvailabilityData(
    val currentMonth: String,
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
