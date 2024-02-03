package com.hulkdx.findprofessional.common.feature.book.time

import com.hulkdx.findprofessional.common.feature.book.time.BookingTimeUiState.BookingTime
import com.hulkdx.findprofessional.common.feature.book.time.BookingTimeUiState.BookingTime.Type.Available
import com.hulkdx.findprofessional.common.feature.book.time.BookingTimeUiState.BookingTime.Type.Selected
import com.hulkdx.findprofessional.common.feature.book.time.BookingTimeUiState.BookingTime.Type.UnAvailable
import com.hulkdx.findprofessional.common.feature.book.time.BookingTimeUtils.currentDay
import com.hulkdx.findprofessional.common.feature.book.time.BookingTimeUtils.isAvailabilityIncludedInTimes
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalAvailability
import com.hulkdx.findprofessional.common.utils.NumberFormatter.twoDigits
import com.hulkdx.findprofessional.common.utils.now
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus

class BookingTimeUseCase(
    now: LocalDate = LocalDate.now(),
) {
    private val date = MutableStateFlow(now)
    private val selectedItems = MutableStateFlow(mapOf<LocalDate, Set<Int>>())

    fun getUiState(professional: Professional): Flow<BookingTimeUiState> =
        combine(date, selectedItems, ::Pair)
            .map { (date, selectedItems) ->
                BookingTimeUiState(
                    currentDate = currentDay(date),
                    times = getTimes(professional, date, selectedItems),
                )
            }

    fun dayMinusOne() {
        date.value = date.value.minus(1, DateTimeUnit.DAY)
    }

    fun dayPlusOne() {
        date.value = date.value.plus(1, DateTimeUnit.DAY)
    }

    fun onTimeClicked(item: BookingTime) {
        selectedItems.update {
            it.toMutableMap()
                .also { updatedMap ->
                    val ids = updatedMap[date.value] ?: setOf()
                    updatedMap[date.value] = if (ids.contains(item.id)) {
                        ids - item.id
                    } else {
                        ids + item.id
                    }
                }
        }
    }

    internal fun getTimes(
        professional: Professional,
        date: LocalDate,
        selectedItems: Map<LocalDate, Set<Int>>,
    ): List<List<BookingTime>> {
        val availability = professional.availability
            .filter { it.date == date }

        val filteredSelectedItems = selectedItems[date] ?: setOf()

        return (0..24 * 60 step 30)
            .windowed(size = 2) { (start, end) ->
                BookingTime(
                    id = start,
                    date = date,
                    startTime = "${twoDigits(start / 60)}:${twoDigits(start % 60)}",
                    endTime = "${twoDigits((end / 60) % 24)}:${twoDigits(end % 60)}",
                    type = getType(start, end, availability, filteredSelectedItems),
                )
            }
            .chunked(2)
    }

    private fun getType(
        start: Int,
        end: Int,
        availability: List<ProfessionalAvailability>,
        selectedItems: Set<Int>,
    ): BookingTime.Type {
        return when {
            selectedItems.contains(start) -> {
                Selected
            }

            availability.any { isAvailabilityIncludedInTimes(it, start, end) } -> {
                Available
            }

            else -> {
                UnAvailable
            }
        }
    }
}
