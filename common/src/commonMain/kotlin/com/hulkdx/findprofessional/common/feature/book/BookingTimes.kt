package com.hulkdx.findprofessional.common.feature.book

data class BookUiState(
    val currentDate: String,
    val times: List<List<BookingTimes>>,
) {

    data class BookingTimes(
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
