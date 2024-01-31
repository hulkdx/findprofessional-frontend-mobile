package com.hulkdx.findprofessional.common.utils

import kotlinx.datetime.LocalTime


fun LocalTime.toMinutesOfDay() = toSecondOfDay() / 60
