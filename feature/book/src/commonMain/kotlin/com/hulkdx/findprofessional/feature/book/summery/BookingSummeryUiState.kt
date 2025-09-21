package com.hulkdx.findprofessional.feature.book.summery

import com.hulkdx.findprofessional.core.utils.StringOrRes

data class BookingSummeryUiState(
    val summeryDetails: SummeryDetails = SummeryDetails(),
    val checkoutStatus: CheckoutStatus = CheckoutStatus.Idle,
    val error: StringOrRes? = null,
) {
    data class SummeryDetails(
        val times: List<Time> = listOf(),
        val userSkypeId: String? = null,
        val totalPrices: String = "",
    ) {

        data class Time(
            val duration: String,
            val date: String,
            val day: String,
        )
    }

    sealed interface CheckoutStatus {
        object Idle : CheckoutStatus
        object Loading : CheckoutStatus
        object Success : CheckoutStatus
    }
}