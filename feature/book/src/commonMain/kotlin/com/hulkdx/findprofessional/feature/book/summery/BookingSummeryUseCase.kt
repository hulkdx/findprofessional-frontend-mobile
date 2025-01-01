package com.hulkdx.findprofessional.feature.book.summery

import com.hulkdx.findprofessional.core.features.book.SelectedTimes
import com.hulkdx.findprofessional.core.features.pro.Professional
import com.hulkdx.findprofessional.core.storage.UserStorage
import com.hulkdx.findprofessional.core.storage.getNormalUser

class BookingSummeryUseCase(
    private val bookingSummeryTimeMapper: BookingSummeryTimeMapper,
    private val userStore: UserStorage,
) {
    suspend fun getUiState(
        professional: Professional,
        times: SelectedTimes,
    ): BookingSummeryUiState {
        val uiTimes = bookingSummeryTimeMapper.map(times)
        val user = userStore.getNormalUser()

        return BookingSummeryUiState(
            times = uiTimes,
            userSkypeId = user?.skypeId,
            totalPrices = bookingSummeryTimeMapper.calculateTotalPrices(professional, uiTimes.size)
        )
    }
}
