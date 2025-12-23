package com.hulkdx.findprofessional.feature.developer

import com.hulkdx.findprofessional.feature.pro.model.ProfessionalAvailability
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atTime
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Instant

fun roundNow(
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): Instant {
    val now = Clock.System.now().toLocalDateTime(timeZone)
    return now.date.atTime((now.time.hour + 1) % 24, 0)
        .toInstant(timeZone)
}

fun createTodayAvailabilities(): List<ProfessionalAvailability> = buildList {
    var id = 1L
    var start = roundNow()
    val end = roundNow() + 1.days
    while (start < end) {
        add(
            ProfessionalAvailability(
                id = id,
                from = start,
                to = start + 30.minutes,
            )
        )
        id++
        start += 30.minutes
    }
}
