package com.hulkdx.findprofessional.feature.book.summery

import com.hulkdx.findprofessional.core.model.book.SelectedTimes
import com.hulkdx.findprofessional.core.model.pro.Professional
import com.hulkdx.findprofessional.core.model.user.User
import com.hulkdx.findprofessional.core.storage.UserStorage

class BookingSummeryUseCase(
    private val bookingSummeryTimeMapper: BookingSummeryTimeMapper,
    private val userStore: UserStorage,
) {
    suspend fun getUiState(
        professional: Professional,
        times: SelectedTimes,
    ): BookingSummeryUiState {
        val uiTimes = bookingSummeryTimeMapper.map(times)
        val user = userStore.get()?.user as? User

        return BookingSummeryUiState(
            times = uiTimes,
            userSkypeId = user?.skypeId,
            totalPrices = bookingSummeryTimeMapper.calculateTotalPrices(professional, uiTimes.size)
        )
    }
}
