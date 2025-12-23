package com.hulkdx.findprofessional.feature.booking.summery

import com.hulkdx.findprofessional.core.features.pro.model.response.CreateBookingResponse
import com.hulkdx.findprofessional.core.utils.StringOrRes

data class BookingSummeryUiState(
    val summeryDetails: SummeryDetails = SummeryDetails(),
    val checkoutStatus: CheckoutStatus = CheckoutStatus.Idle,
    val isLoading: Boolean = false,
    val error: StringOrRes? = null,
) {
    data class SummeryDetails(
        val times: List<Time> = listOf(),
        val userSkypeId: String? = null,
        val amountInCents: Long = 0,
        val currency: String = "",
        val formattedTotalPrices: String = "",
    ) {
        data class Time(
            val duration: String,
            val date: String,
            val day: String,
        )
    }

    sealed interface CheckoutStatus {
        object Idle : CheckoutStatus
        data class Success(val result: CreateBookingResponse) : CheckoutStatus
    }
}
