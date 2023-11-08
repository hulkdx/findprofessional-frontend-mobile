package com.hulkdx.findprofessional.common.utils

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.DayOfWeek.*


fun DayOfWeek.toShort(): String {
    return when (this) {
        MONDAY -> "Mon"
        TUESDAY -> "Tue"
        WEDNESDAY -> "Wed"
        THURSDAY -> "Thu"
        FRIDAY -> "Fri"
        SATURDAY -> "Sat"
        SUNDAY -> "Sun"
        else -> throw RuntimeException("Invalid")
    }
}
