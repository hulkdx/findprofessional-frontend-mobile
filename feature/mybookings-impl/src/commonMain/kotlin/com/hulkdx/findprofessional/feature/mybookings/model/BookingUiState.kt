package com.hulkdx.findprofessional.feature.mybookings.model

data class BookingUiState(
    val items: List<Item>,
) {
    data class Item(
        val id: String,
        val dayLabel: String,
        val dayNumber: String,
        val fullName: String,
        val status: BookingStatus,
        val type: String,
        val startTime: String,
        val canJoinSession: Boolean = true,
        val canCancel: Boolean = true,
    )
}

enum class BookingStatus {
    Confirmed,
    Canceled,
}
