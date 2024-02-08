package com.hulkdx.findprofessional.feature.book.summery


data class BookingSummeryUiState(
    val times: List<Time>,
    val userEmail: String,
    val totalPrices: String,
) {

    data class Time(
        val duration: String,
        val date: String,
        val day: String,
    )
}
