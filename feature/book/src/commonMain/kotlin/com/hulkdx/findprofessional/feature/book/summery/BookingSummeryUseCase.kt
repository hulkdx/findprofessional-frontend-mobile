package com.hulkdx.findprofessional.feature.book.summery

import com.hulkdx.findprofessional.core.model.book.SelectedTimes
import com.hulkdx.findprofessional.core.model.pro.Professional
import com.hulkdx.findprofessional.feature.authentication.login.storage.datastore.UserStorageDataStore

class BookingSummeryUseCase(
    private val bookingSummeryTimeMapper: BookingSummeryTimeMapper,
    private val userStore: UserStorageDataStore,
) {
    suspend fun getUiState(
        professional: Professional,
        times: SelectedTimes,
    ): BookingSummeryUiState {
        val uiTimes = bookingSummeryTimeMapper.map(times)
        val user = userStore.get()?.user

        return BookingSummeryUiState(
            times = uiTimes,
            userSkypeId = user?.skypeId,
            totalPrices = bookingSummeryTimeMapper.calculateTotalPrices(professional, uiTimes.size)
        )
    }
}
