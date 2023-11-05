package com.hulkdx.findprofessional.common.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.toInstant

fun clock(now: String) = object : Clock {
    override fun now() = now.toInstant()
}
