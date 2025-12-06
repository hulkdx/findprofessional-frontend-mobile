package com.hulkdx.findprofessional.core.utils

object TimeUtils {
    // Formatted time in HH:MM
    fun formattedTime(minute: Int): String {
        val hour = ((minute / 60) % 24).toString().padStart(2, '0')
        val min = (minute % 60).toString().padStart(2, '0')

        return "$hour:$min"
    }
}
