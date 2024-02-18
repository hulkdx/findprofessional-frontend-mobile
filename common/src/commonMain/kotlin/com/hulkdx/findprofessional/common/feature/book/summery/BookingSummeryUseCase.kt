package com.hulkdx.findprofessional.common.feature.book.summery

import com.hulkdx.findprofessional.common.feature.book.time.SelectedTimes
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BookingSummeryUseCase(
    private val bookingSummeryTimeMapper: BookingSummeryTimeMapper,
) {
    fun getUiState(professional: Professional, times: SelectedTimes): Flow<BookingSummeryUiState> =
        flow { emit(bookingSummeryTimeMapper.map(times)) }

}
