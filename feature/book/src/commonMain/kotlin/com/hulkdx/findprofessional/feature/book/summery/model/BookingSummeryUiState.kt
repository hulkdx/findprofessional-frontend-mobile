package com.hulkdx.findprofessional.feature.book.summery.model

data class BookingSummeryUiState(
    val times: List<Time>,
    val userSkypeId: String?,
    val totalPrices: String,
) {

    data class Time(
        val duration: String,
        val date: String,
        val day: String,
    )
}
