package com.hulkdx.findprofessional.common.feature.book.time

import com.hulkdx.findprofessional.common.utils.CommonParcelable
import com.hulkdx.findprofessional.common.utils.CommonParcelize
import com.hulkdx.findprofessional.common.utils.CommonTypeParceler
import com.hulkdx.findprofessional.common.utils.LocalDateParceler
import kotlinx.datetime.LocalDate

@CommonParcelize
@CommonTypeParceler<LocalDate, LocalDateParceler>
data class SelectedTimes(
    val items: Map<LocalDate, Set<Int>> = mapOf(),
) : CommonParcelable
