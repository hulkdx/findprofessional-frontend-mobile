package com.hulkdx.findprofessional.common.feature.book

data class BookUiState(
    val currentDate: String,
    val times: List<List<BookingTime>>,
) {

    data class BookingTime(
        val id: Int,
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
