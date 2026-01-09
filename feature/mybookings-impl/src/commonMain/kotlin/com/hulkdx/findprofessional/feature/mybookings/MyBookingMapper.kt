package com.hulkdx.findprofessional.feature.mybookings

import com.hulkdx.findprofessional.core.utils.shortDayOfWeeks
import com.hulkdx.findprofessional.feature.mybookings.model.BookingUiState
import com.hulkdx.findprofessional.feature.mybookings.model.MyBookingSegment
import com.hulkdx.findprofessional.feature.pro.model.Booking
import com.hulkdx.findprofessional.feature.pro.model.Booking.Status.CONFIRMED
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Instant

fun List<Booking>.toUiState(
    segment: MyBookingSegment,
    now: Instant,
    timeZone: TimeZone,
): List<BookingUiState.Item> {
    return filter {
        when (segment) {
            MyBookingSegment.Upcoming -> {
                it.scheduledEndAt > now
            }

            MyBookingSegment.Past -> {
                it.scheduledEndAt <= now
            }
        }
    }.map { it.toUiState(timeZone, now) }
}

fun Booking.toUiState(
    timeZone: TimeZone,
    now: Instant,
): BookingUiState.Item {
    val startTime = scheduledStartAt.toLocalDateTime(timeZone)

    val earlyJoin = 30.minutes
    val joinGrace = 30.minutes

    val canJoin = status == CONFIRMED &&
            now >= (scheduledStartAt - earlyJoin) &&
            now <= (scheduledEndAt + joinGrace)

    val canCancel = status == CONFIRMED &&
            now < scheduledStartAt

    return BookingUiState.Item(
        id = id.toString(),
        dayLabel = startTime.date.shortDayOfWeeks(),
        dayNumber = startTime.date.day.toString(),
        fullName = "${professional.firstName} ${professional.lastName}",
        status = status,
        startTime = "${startTime.time} $timeZone",
        canJoinSession = canJoin,
        canCancel = canCancel,
        session = session,
    )
}
