package com.hulkdx.findprofessional.core.utils

import com.hulkdx.findprofessional.core.utils.NumberFormatter.twoDigits

object TimeUtils {
    // Formatted time in HH:MM
    fun formattedTime(minute: Int): String {
        val hour = twoDigits((minute / 60) % 24)
        val min = twoDigits(minute % 60)

        return "$hour:$min"
    }
}
