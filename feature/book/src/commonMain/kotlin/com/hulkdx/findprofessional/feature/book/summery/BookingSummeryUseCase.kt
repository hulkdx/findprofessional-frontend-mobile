package com.hulkdx.findprofessional.feature.book.summery

import com.hulkdx.findprofessional.core.features.book.SelectedTimes
import com.hulkdx.findprofessional.core.features.pro.model.Professional
import com.hulkdx.findprofessional.core.features.user.User
import com.hulkdx.findprofessional.core.storage.UserStorage
import com.hulkdx.findprofessional.core.storage.getNormalUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookingSummeryUseCase(
    private val bookingSummeryTimeMapper: BookingSummeryTimeMapper,
    private val userStore: UserStorage,
) {
    fun getUiState(
        professional: Professional,
        times: SelectedTimes,
    ): Flow<BookingSummeryUiState> {
        val uiTimes = bookingSummeryTimeMapper.map(times)
        return userStore.getFlow()
            .map {
                BookingSummeryUiState(
                    times = uiTimes,
                    userSkypeId = (it?.user as? User)?.skypeId,
                    totalPrices = bookingSummeryTimeMapper.calculateTotalPrices(professional, uiTimes.size)
                )
            }
    }
}
