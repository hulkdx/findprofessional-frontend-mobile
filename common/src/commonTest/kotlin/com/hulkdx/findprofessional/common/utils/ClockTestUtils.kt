package com.hulkdx.findprofessional.common.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.toInstant

fun mockClock(nowDate: String) = object : Clock {
    override fun now() = "${nowDate}T00:00:00.Z".toInstant()
}
