package com.hulkdx.findprofessional.core.utils

import kotlinx.datetime.LocalTime


fun LocalTime.toMinutesOfDay() = toSecondOfDay() / 60
