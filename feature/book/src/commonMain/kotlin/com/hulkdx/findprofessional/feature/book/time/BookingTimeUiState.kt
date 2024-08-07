package com.hulkdx.findprofessional.feature.book.time

import kotlinx.datetime.LocalDate

data class BookingTimeUiState(
    val currentDate: String,
    val times: List<List<BookingTime>>,
) {

    data class BookingTime(
        val id: Int,
        val date: LocalDate,
        val startTime: String,
        val endTime: String,
        val type: Type,
    ) {
        enum class Type {
            Available,
            UnAvailable,
            Selected,
        }
    }
}
