package com.hulkdx.findprofessional.common.feature.home.model

import com.hulkdx.findprofessional.common.utils.CommonParcelable
import com.hulkdx.findprofessional.common.utils.CommonParcelize
import com.hulkdx.findprofessional.common.utils.CommonTypeParceler
import com.hulkdx.findprofessional.common.utils.LocalDateParceler
import com.hulkdx.findprofessional.common.utils.LocalTimeParceler
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.serialization.Serializable

/**
 * from 0 time to 0 time means its available the whole day
 */
@CommonParcelize
@Serializable
@CommonTypeParceler<LocalDate, LocalDateParceler>
@CommonTypeParceler<LocalTime, LocalTimeParceler>
data class ProfessionalAvailability(
    val date: LocalDate,
    val from: LocalTime,
    val to: LocalTime,
) : CommonParcelable
