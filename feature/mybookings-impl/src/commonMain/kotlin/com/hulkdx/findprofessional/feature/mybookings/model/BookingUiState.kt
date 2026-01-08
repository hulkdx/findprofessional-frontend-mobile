package com.hulkdx.findprofessional.feature.mybookings.model

import com.hulkdx.findprofessional.core.utils.StringOrRes
import com.hulkdx.findprofessional.feature.pro.model.Booking
import com.hulkdx.findprofessional.feature.pro.model.Booking.Status

data class BookingUiState(
    val isLoading: Boolean = true,
    val error: StringOrRes? = null,
    val segment: MyBookingSegment = MyBookingSegment.Upcoming,
    val items: List<Item> = listOf(),
    val navigation: Navigation? = null,
) {
    data class Item(
        val id: String,
        val dayLabel: String,
        val dayNumber: String,
        val fullName: String,
        val status: Status,
        val startTime: String,
        val canJoinSession: Boolean = true,
        val canCancel: Boolean = true,
        val session: Booking.SessionInfo,
    )

    sealed interface Navigation {
        data class OpenUrl(val url: String) : Navigation
    }
}
