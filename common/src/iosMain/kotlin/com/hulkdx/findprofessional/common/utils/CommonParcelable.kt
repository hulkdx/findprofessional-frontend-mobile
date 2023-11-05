package com.hulkdx.findprofessional.common.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

actual interface CommonParcelable
actual interface CommonParceler<T>
actual annotation class CommonTypeParceler<T, P : CommonParceler<in T>>
actual object LocalDateParceler : CommonParceler<LocalDate?>
actual object LocalTimeParceler : CommonParceler<LocalTime?>
