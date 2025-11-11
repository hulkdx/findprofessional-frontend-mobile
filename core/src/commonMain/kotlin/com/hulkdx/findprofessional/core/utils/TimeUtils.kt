package com.hulkdx.findprofessional.core.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object TimeUtils {
    // Formatted time in HH:MM
    fun formattedTime(minute: Int): String {
        val hour = ((minute / 60) % 24).toString().padStart(2, '0')
        val min = (minute % 60).toString().padStart(2, '0')

        return "$hour:$min"
    }

    fun getMinutes(instant: Instant): Int {
        val formattedTime = instant.toLocalDateTime(timeZone = TimeZone.UTC).time.toString()
        val parts = formattedTime.split(":")
        if (parts.size != 2) return 0
        val hour = parts[0].toIntOrNull() ?: 0
        val min = parts[1].toIntOrNull() ?: 0
        return (hour * 60) + min
    }
}
