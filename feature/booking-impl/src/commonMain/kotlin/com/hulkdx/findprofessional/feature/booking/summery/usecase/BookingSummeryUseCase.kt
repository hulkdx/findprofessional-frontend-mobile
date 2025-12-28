package com.hulkdx.findprofessional.feature.booking.summery.usecase

import com.hulkdx.findprofessional.feature.booking.summery.BookingSummeryTimeMapper
import com.hulkdx.findprofessional.feature.booking.summery.BookingSummeryUiState.SummeryDetails
import com.hulkdx.findprofessional.feature.pro.model.Professional
import com.hulkdx.findprofessional.feature.pro.model.ProfessionalAvailability
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class BookingSummeryUseCase(
    private val bookingSummeryTimeMapper: BookingSummeryTimeMapper,
) {
    fun getSummeryDetails(
        professional: Professional,
        availabilities: List<ProfessionalAvailability>,
    ): Flow<SummeryDetails> {
        val times = bookingSummeryTimeMapper.map(availabilities)
        val amountInCents = professional.priceNumber * times.size
        val currency = professional.priceCurrency
        val formattedTotalPrices = bookingSummeryTimeMapper.formattedTotalPrices(
            amountInCents = amountInCents,
            currency = currency,
        )

        return flowOf(
            SummeryDetails(
                times = times,
                amountInCents = amountInCents,
                currency = currency,
                formattedTotalPrices = formattedTotalPrices,
            )
        )
    }
}
