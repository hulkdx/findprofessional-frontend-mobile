package com.hulkdx.findprofessional.feature.book.summery.usecase

import com.hulkdx.findprofessional.core.features.book.SelectedTimes
import com.hulkdx.findprofessional.core.features.pro.model.Professional
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
        times: SelectedTimes,
    ): Flow<SummeryDetails> {
        val uiTimes = bookingSummeryTimeMapper.map(times)
        return userStore.getFlow()
            .map {
                SummeryDetails(
                    times = uiTimes,
                    userSkypeId = (it?.user as? User)?.skypeId,
                    formattedTotalPrices = bookingSummeryTimeMapper.calculateTotalPrices(
                        professional,
                        uiTimes.size
                    )
                )
            }
    }
}
