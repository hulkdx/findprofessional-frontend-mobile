package com.hulkdx.findprofessional.feature.book.summery.usecase

import com.hulkdx.findprofessional.core.features.pro.model.Professional
import com.hulkdx.findprofessional.core.features.pro.model.ProfessionalAvailability
import com.hulkdx.findprofessional.core.features.user.User
import com.hulkdx.findprofessional.core.storage.UserStorage
import com.hulkdx.findprofessional.feature.book.summery.BookingSummeryTimeMapper
import com.hulkdx.findprofessional.feature.book.summery.BookingSummeryUiState.SummeryDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookingSummeryUseCase(
    private val bookingSummeryTimeMapper: BookingSummeryTimeMapper,
    private val userStore: UserStorage,
) {
    fun getSummeryDetails(
        professional: Professional,
        availabilities: List<ProfessionalAvailability>,
    ): Flow<SummeryDetails> {
        val times = bookingSummeryTimeMapper.map(availabilities)
        return userStore.getFlow()
            .map {
                val amountInCents = professional.priceNumber * times.size
                val currency = professional.priceCurrency
                val formattedTotalPrices = bookingSummeryTimeMapper.formattedTotalPrices(
                    amountInCents = amountInCents,
                    currency = currency,
                )

                SummeryDetails(
                    times = times,
                    userSkypeId = (it?.user as? User)?.skypeId,
                    amountInCents = amountInCents,
                    currency = currency,
                    formattedTotalPrices = formattedTotalPrices,
                )
            }
    }
}
