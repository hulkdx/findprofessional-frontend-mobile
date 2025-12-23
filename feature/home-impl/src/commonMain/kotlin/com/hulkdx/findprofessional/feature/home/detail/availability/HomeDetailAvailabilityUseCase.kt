package com.hulkdx.findprofessional.feature.home.detail.availability

import com.hulkdx.findprofessional.core.features.pro.model.Professional
import com.hulkdx.findprofessional.core.utils.DateUtils
import com.hulkdx.findprofessional.core.utils.now
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.datetime.DateTimeUnit.Companion.MONTH
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus

class HomeDetailAvailabilityUseCase {
    private val date = MutableStateFlow(LocalDate.now())

    fun getAvailabilityData(professional: Professional): Flow<AvailabilityData> {
        return date
            .map { date ->
                AvailabilityData(
                    currentMonth = DateUtils.formatToMonthsAndYear(date),
                    firstDay = DateUtils.firstDayInt(date),
                    lengthOfMonth = DateUtils.lengthOfMonth(date),
                    now = date,
                    professionalAvailabilityDates = professional.availability.map { it.date },
                )
            }
            .distinctUntilChanged()
    }

    fun monthMinusOne() {
        date.value = date.value.minus(1, MONTH)
    }

    fun monthPlusOne() {
        date.value = date.value.plus(1, MONTH)
    }
}
