package com.hulkdx.findprofessional.common.feature.home.utils

import com.hulkdx.findprofessional.common.feature.home.model.Availability
import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalAvailability
import com.hulkdx.findprofessional.common.utils.toShort
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime


fun List<ProfessionalAvailability>.mapToUi(
    clock: Clock,
    timeZone: TimeZone = TimeZone.UTC,
): Availability {
    val startDate = clock.now()
    val d = 7
    val t = 7

    val items = betweenDates(
        startDate,
        startDate.plus(d, DateTimeUnit.DAY, timeZone),
        timeZone,
    )

    val result = MutableList(t) { i ->
        if (i == 0) mutableListOf("")
        else MutableList(d) { j ->
            if (j == 0) {
                timeColumn(i)
            } else {
                "0"
            }
        }
    }
    for (i in 0..<d - 1) {
        val currentDate = startDate.plus(i, DateTimeUnit.DAY, timeZone).toLocalDateTime(timeZone)
        result[0] += currentDate.topRowFormat()

        for (x in items) {
            if (x.date == currentDate.date) {
                for (j in 1..<t) {
                    result[j][i + 1] = calculateDuration(x, j - 1)
                }
            }
        }
    }

    return result
}

private fun timeColumn(x: Int): String {
    val time = (x - 1) * 4
    val nextTime = time + 4
    return "${time.twoDigits()}-${nextTime.twoDigits()}"
}

private fun Int.twoDigits(): String {
    return if (this / 10 == 0) {
        "0$this"
    } else {
        "$this"
    }
}

private fun List<ProfessionalAvailability>.betweenDates(
    startDate: Instant,
    endDate: Instant,
    timeZone: TimeZone,
): List<ProfessionalAvailability> {
    val s = startDate.toLocalDateTime(timeZone).date
    val e = endDate.toLocalDateTime(timeZone).date
    return this.filter {
        it.date in s..e
    }.sortedBy { it.date }
}

private fun LocalDateTime.topRowFormat(): String {
    return "${dayOfWeek.toShort()}\n${dayOfMonth}"
}

// index 0 means time between 0 to 4, etc...
internal fun calculateDuration(
    a: ProfessionalAvailability,
    index: Int,
): String {
    val startTime = index * 4
    val endTime = (index + 1) * 4

    val fromHour = a.from.hour
    val toHour = if (a.to.hour == 0 && a.from.hour > 0) 24 else a.to.hour

    if (fromHour < endTime && toHour > startTime) {
        if (toHour > endTime) {
            return (4 - fromHour).toString()
        }
        val diff = toHour - fromHour
        val mod = diff.mod(4)
        val res = if (diff > 0 && mod == 0) 4 else mod
        return res.toString()
    }
    return "0"
}
