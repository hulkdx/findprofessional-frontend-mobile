package com.hulkdx.findprofessional.feature.booking.summery

import com.hulkdx.findprofessional.core.utils.StringOrRes
import com.hulkdx.findprofessional.feature.pro.model.response.CreateBookingResponse

data class BookingSummeryUiState(
    val summeryDetails: SummeryDetails = SummeryDetails(),
    val checkoutStatus: CheckoutStatus = CheckoutStatus.Idle,
    val isLoading: Boolean = false,
    val error: StringOrRes? = null,
) {
    data class SummeryDetails(
        val times: List<Time> = listOf(),
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
