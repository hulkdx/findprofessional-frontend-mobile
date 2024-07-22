package com.hulkdx.findprofessional.feature.book.time

import com.hulkdx.findprofessional.core.model.book.SelectedTimes
import com.hulkdx.findprofessional.core.model.pro.Professional
import com.hulkdx.findprofessional.core.model.pro.ProfessionalAvailability
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.core.utils.now
import com.hulkdx.findprofessional.feature.book.time.BookingTimeUiState.BookingTime
import com.hulkdx.findprofessional.feature.book.time.BookingTimeUiState.BookingTime.Type.Available
import com.hulkdx.findprofessional.feature.book.time.BookingTimeUiState.BookingTime.Type.Selected
import com.hulkdx.findprofessional.feature.book.time.BookingTimeUiState.BookingTime.Type.UnAvailable
import com.hulkdx.findprofessional.feature.book.time.utils.BookingTimeUtils.currentDay
import com.hulkdx.findprofessional.feature.book.time.utils.BookingTimeUtils.formattedTime
import com.hulkdx.findprofessional.feature.book.time.utils.BookingTimeUtils.isAvailabilityIncludedInTimes
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
    private val navigator: Navigator,
) {
    private val date = MutableStateFlow(now)
    private val selectedItems = MutableStateFlow(SelectedTimes())

    fun getUiState(professional: Professional): Flow<BookingTimeUiState> =
        combine(date, selectedItems, ::Pair)
            .map { (date, selectedItems) ->
                BookingTimeUiState(
                    currentDate = currentDay(date),
                    times = getTimes(professional, date, selectedItems.items),
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
            val newItems = it.items.toMutableMap()
                .also { updatedMap ->
                    val ids = updatedMap[date.value] ?: setOf()
                    updatedMap[date.value] = if (ids.contains(item.id)) {
                        ids - item.id
                    } else {
                        ids + item.id
                    }
                }
            SelectedTimes(newItems)
        }
    }

    fun onContinueClicked(professional: Professional) {
        navigator.navigate(NavigationScreen.BookingSummery(professional, selectedItems.value))
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
                    startTime = formattedTime(start),
                    endTime = formattedTime(end),
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
