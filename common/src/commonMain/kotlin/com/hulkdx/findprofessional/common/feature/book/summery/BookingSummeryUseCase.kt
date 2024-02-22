package com.hulkdx.findprofessional.common.feature.book.summery

import com.hulkdx.findprofessional.common.config.storage.datastore.UserStorageDataStore
import com.hulkdx.findprofessional.common.feature.book.time.SelectedTimes
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull

class BookingSummeryUseCase(
    private val bookingSummeryTimeMapper: BookingSummeryTimeMapper,
    private val userStore: UserStorageDataStore,
) {
    fun getUiState(professional: Professional, times: SelectedTimes) =
        combine(getTimes(times), getUser(), ::Pair)
            .map { (uiTimes, user) ->
                BookingSummeryUiState(
                    uiTimes,
                    user.skypeId,
                    bookingSummeryTimeMapper.calculateTotalPrices(professional, uiTimes.size)
                )
            }

    private fun getTimes(times: SelectedTimes) =
        flow { emit(bookingSummeryTimeMapper.map(times)) }

    private fun getUser() = flow { emit(userStore.get()) }
        .mapNotNull { it }
        .flowOn(Dispatchers.IO)
}
