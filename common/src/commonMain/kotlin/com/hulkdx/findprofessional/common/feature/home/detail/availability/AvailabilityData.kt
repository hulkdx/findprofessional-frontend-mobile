package com.hulkdx.findprofessional.common.feature.home.detail.availability

import com.hulkdx.findprofessional.common.utils.CommonParcelable
import com.hulkdx.findprofessional.common.utils.CommonParcelize
import com.hulkdx.findprofessional.common.utils.CommonTypeParceler
import com.hulkdx.findprofessional.common.utils.LocalDateParceler
import dev.icerock.moko.parcelize.IgnoredOnParcel
import kotlinx.datetime.LocalDate
import kotlin.math.ceil

@CommonParcelize
@CommonTypeParceler<LocalDate, LocalDateParceler>
data class AvailabilityData(
    val currentMonth: String,
    val firstDay: Int,
    val lengthOfMonth: Int,
    val now: Int, // TODO: use LocalDate
    val professionalAvailabilityDates: List<LocalDate>,
) : CommonParcelable {

    @IgnoredOnParcel
    val nowLocalDate: LocalDate = LocalDate.fromEpochDays(now)

    val perWeek = ceil((lengthOfMonth + firstDay) / 7F).toInt()

    fun isSelectedDay(day: Int): Boolean {
        val checkDay = LocalDate(nowLocalDate.year, nowLocalDate.month, day)
        return professionalAvailabilityDates.contains(checkDay)
    }
}
