package com.hulkdx.findprofessional.common.feature.book.summery

import com.hulkdx.findprofessional.common.config.storage.datastore.UserStorageDataStore
import com.hulkdx.findprofessional.common.feature.book.time.SelectedTimes
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
    fun getUiState(times: SelectedTimes) =
        combine(getTimes(times), getUser(), ::Pair)
            .map { (uiTimes, user) ->
                // TODO: totalPrices
                BookingSummeryUiState(uiTimes, user.skypeId, "")
            }

    private fun getTimes(times: SelectedTimes) =
        flow { emit(bookingSummeryTimeMapper.map(times)) }

    private fun getUser() = flow { emit(userStore.get()) }
        .mapNotNull { it }
        .flowOn(Dispatchers.IO)

}
