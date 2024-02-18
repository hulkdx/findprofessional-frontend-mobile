package com.hulkdx.findprofessional.common.feature.book.summery

import com.hulkdx.findprofessional.common.feature.book.time.SelectedTimes
import kotlinx.datetime.LocalDate

class BookingSummeryTimeMapper {
    fun map(selectedTimes: SelectedTimes): BookingSummeryUiState {
        val times = selectedTimes.items.map { (key, value) -> map(key to value) }
        TODO()
    }

    fun map(item: Pair<LocalDate, Set<Int>>): BookingSummeryUiState.Time {
        TODO()
    }
}
