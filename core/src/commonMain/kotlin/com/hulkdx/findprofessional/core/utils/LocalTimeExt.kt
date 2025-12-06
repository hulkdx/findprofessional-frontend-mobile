package com.hulkdx.findprofessional.core.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


fun LocalTime.toMinutesOfDay() = toSecondOfDay() / 60
fun Instant.toMinutesOfDay(timeZone: TimeZone) = toLocalDateTime(timeZone).time.toMinutesOfDay()
