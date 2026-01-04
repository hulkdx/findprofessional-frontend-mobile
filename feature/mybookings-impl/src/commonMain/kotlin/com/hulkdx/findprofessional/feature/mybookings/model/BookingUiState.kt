package com.hulkdx.findprofessional.feature.mybookings.model

import com.hulkdx.findprofessional.core.utils.StringOrRes

data class BookingUiState(
    val isLoading: Boolean = true,
    val error: StringOrRes? = null,
    val segment: MyBookingSegment = MyBookingSegment.Upcoming,
    val items: List<Item> = listOf(),
) {
    data class Item(
        val id: String,
        val dayLabel: String,
        val dayNumber: String,
        val fullName: String,
        val status: BookingStatus,
        val startTime: String,
        val canJoinSession: Boolean = true,
        val canCancel: Boolean = true,
    )
}

// TODO: remove and replace with other status
enum class BookingStatus {
    Pending,
    Confirmed,
    Completed,
    Failed,
    Unknown,
    ;

    companion object {
        fun valueOfOrUnknown(value: String): BookingStatus {
            return entries.firstOrNull { it.name.lowercase() == value } ?: Unknown
        }
    }
}
