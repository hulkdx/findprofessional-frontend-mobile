package com.hulkdx.findprofessional.feature.pro.schedule.model

import com.hulkdx.findprofessional.core.utils.StringOrRes
import com.hulkdx.findprofessional.feature.pro.model.Booking
import com.hulkdx.findprofessional.feature.pro.model.Booking.Status

data class ScheduleUiState(
    val segment: Segment = Segment.Upcoming,
    val isRefreshing: Boolean = false,
    val error: StringOrRes? = null,
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
        val session: Booking.SessionInfo,
        val canJoin: Boolean,
    )

    sealed interface Navigation {
        data class OpenUrl(val url: String) : Navigation
    }
}

enum class Segment {
    Upcoming,
    Past,
}
