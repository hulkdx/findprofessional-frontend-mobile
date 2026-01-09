package com.hulkdx.findprofessional.feature.pro.schedule.mapper

import com.hulkdx.findprofessional.core.utils.shortDayOfWeeks
import com.hulkdx.findprofessional.feature.pro.model.Booking
import com.hulkdx.findprofessional.feature.pro.model.Booking.Status.COMPLETED
import com.hulkdx.findprofessional.feature.pro.model.Booking.Status.CONFIRMED
import com.hulkdx.findprofessional.feature.pro.model.Booking.Status.PENDING
import com.hulkdx.findprofessional.feature.pro.schedule.model.ScheduleUiState.Item
import com.hulkdx.findprofessional.feature.pro.schedule.model.Segment
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

fun List<Booking>.toUiState(
    segment: Segment,
    now: Instant,
    timeZone: TimeZone,
): List<Item> {
    return filter {
        when (segment) {
            Segment.Upcoming -> {
                it.scheduledEndAt > now
            }

            Segment.Past -> {
                it.scheduledEndAt <= now
            }
        }
    }.map { it.toUiState(timeZone, segment) }
}

private fun Booking.toUiState(
    timeZone: TimeZone,
    segment: Segment,
): Item {
    val startTime = scheduledStartAt.toLocalDateTime(timeZone)

    val canJoin = segment != Segment.Past &&
            status in listOf(PENDING, CONFIRMED, COMPLETED)

    return Item(
        id = id.toString(),
        dayLabel = startTime.date.shortDayOfWeeks(),
        dayNumber = startTime.date.day.toString(),
        fullName = "${user.firstName} ${user.lastName}",
        status = status,
        startTime = "${startTime.time} $timeZone",
        session = session,
        canJoin = canJoin,
    )
}
