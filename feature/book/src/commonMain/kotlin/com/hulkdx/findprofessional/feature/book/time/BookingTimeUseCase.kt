package com.hulkdx.findprofessional.feature.book.time

import com.hulkdx.findprofessional.core.features.book.SelectedTimes
import com.hulkdx.findprofessional.core.features.pro.model.Professional
import com.hulkdx.findprofessional.core.features.pro.model.ProfessionalAvailability
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.core.utils.TimeUtils
import com.hulkdx.findprofessional.core.utils.TimeUtils.formattedTime
import com.hulkdx.findprofessional.core.utils.now
import com.hulkdx.findprofessional.core.utils.toMinutesOfDay
import com.hulkdx.findprofessional.feature.book.time.BookingTimeUiState.BookingTime
import com.hulkdx.findprofessional.feature.book.time.BookingTimeUiState.BookingTime.Type.Available
import com.hulkdx.findprofessional.feature.book.time.BookingTimeUiState.BookingTime.Type.Selected
import com.hulkdx.findprofessional.feature.book.time.BookingTimeUiState.BookingTime.Type.UnAvailable
import com.hulkdx.findprofessional.feature.book.time.utils.BookingTimeUtils.currentDay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus

class BookingTimeUseCase(
    now: LocalDate = LocalDate.now(),
    private val navigator: Navigator,
) {
    private val professionalAvailabilityMap = mutableMapOf<LocalDate, Map<Int, ProfessionalAvailability>>()
    private val date = MutableStateFlow(now)
    private val selectedItems = MutableStateFlow(SelectedTimes())

    fun getUiState(
        professional: Professional,
        timeZone: TimeZone = TimeZone.currentSystemDefault()
    ): Flow<BookingTimeUiState> {
        if (professionalAvailabilityMap.isEmpty()) {
            for (a in professional.availability) {
                professionalAvailabilityMap[a.date] =
                    (professionalAvailabilityMap[a.date] ?: mutableMapOf()) +
                            (a.from.toMinutesOfDay(timeZone) to a)
            }
        }

        return combine(date, selectedItems, ::Pair)
            .map { (date, selectedItems) ->
                BookingTimeUiState(
                    currentDate = currentDay(date),
                    times = getTimes(date, selectedItems.items),
                )
            }
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

    fun hasSelectedItems(): Boolean {
        return selectedItems.value.items.isNotEmpty()
    }

    fun onContinueClicked(professional: Professional) {
        val availabilities = mutableListOf<ProfessionalAvailability>()
        for ((date, times) in selectedItems.value.items) {
            for (t in times) {
                val av = professionalAvailabilityMap[date]?.get(t)
                if (av != null) {
                    availabilities.add(av)
                }
            }
        }
        navigator.navigate(NavigationScreen.BookingSummery(professional, availabilities))
    }

    internal fun getTimes(
        date: LocalDate,
        selectedItems: Map<LocalDate, Set<Int>>,
    ): List<List<BookingTime>> {
        return (0..24 * 60 step 30)
            .windowed(size = 2) { (start, end) ->
                val startTime = formattedTime(start)
                BookingTime(
                    id = start,
                    startTime = startTime,
                    endTime = formattedTime(end),
                    type = getType(start, date, selectedItems),
                )
            }
            .chunked(2)
    }

    private fun getType(
        start: Int,
        date: LocalDate,
        selectedItems: Map<LocalDate, Set<Int>>,
    ): BookingTime.Type {
        return when {
            selectedItems[date]?.contains(start) == true -> {
                Selected
            }

            professionalAvailabilityMap[date]?.contains(start) == true -> {
                Available
            }

            else -> {
                UnAvailable
            }
        }
    }
}
