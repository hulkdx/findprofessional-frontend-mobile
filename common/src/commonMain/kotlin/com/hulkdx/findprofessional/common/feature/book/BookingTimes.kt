package com.hulkdx.findprofessional.common.feature.book

import kotlinx.datetime.LocalDate

data class BookUiState(
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
