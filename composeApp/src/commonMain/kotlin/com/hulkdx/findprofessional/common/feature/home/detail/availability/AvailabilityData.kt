package com.hulkdx.findprofessional.common.feature.home.detail.availability

import com.hulkdx.findprofessional.common.utils.CommonParcelable
import com.hulkdx.findprofessional.common.utils.CommonParcelize
import com.hulkdx.findprofessional.common.utils.CommonTypeParceler
import com.hulkdx.findprofessional.common.utils.LocalDateParceler
import kotlinx.datetime.LocalDate
import kotlin.math.ceil

@CommonParcelize
@CommonTypeParceler<LocalDate, LocalDateParceler>
data class AvailabilityData(
    val currentMonth: String,
    val firstDay: Int,
    val lengthOfMonth: Int,
    val now: LocalDate,
    val professionalAvailabilityDates: List<LocalDate>,
) : CommonParcelable {

    val perWeek = ceil((lengthOfMonth + firstDay) / 7F).toInt()

    fun isSelectedDay(day: Int): Boolean {
        val checkDay = LocalDate(now.year, now.month, day)
        return professionalAvailabilityDates.contains(checkDay)
    }
}
