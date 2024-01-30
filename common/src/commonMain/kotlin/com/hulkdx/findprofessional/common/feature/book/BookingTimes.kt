package com.hulkdx.findprofessional.common.feature.book

data class BookingTimes(
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
