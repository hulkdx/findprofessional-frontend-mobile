package com.hulkdx.findprofessional.feature.mybookings

import com.hulkdx.findprofessional.core.utils.shortDayOfWeeks
import com.hulkdx.findprofessional.feature.mybookings.model.BookingStatus
import com.hulkdx.findprofessional.feature.mybookings.model.BookingUiState
import com.hulkdx.findprofessional.feature.mybookings.model.MyBookingSegment
import com.hulkdx.findprofessional.feature.pro.model.Booking
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
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
    val bookingStatus = BookingStatus.valueOfOrUnknown(status)

    val canJoin = bookingStatus == BookingStatus.Confirmed && (scheduledEndAt < now)
    val canCancel = bookingStatus in listOf(BookingStatus.Confirmed, BookingStatus.Completed)

    return BookingUiState.Item(
        id = id.toString(),
        dayLabel = startTime.date.shortDayOfWeeks(),
        dayNumber = startTime.date.day.toString(),
        fullName = "${professional.firstName} ${professional.lastName}",
        status = bookingStatus,
        startTime = "${startTime.time} $timeZone",
        canJoinSession = canJoin,
        canCancel = canCancel,
    )
}
