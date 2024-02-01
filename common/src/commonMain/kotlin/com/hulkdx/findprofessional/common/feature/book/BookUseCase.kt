package com.hulkdx.findprofessional.common.feature.book

import com.hulkdx.findprofessional.common.feature.book.BookUiState.BookingTimes
import com.hulkdx.findprofessional.common.feature.book.BookUiState.BookingTimes.Type.Available
import com.hulkdx.findprofessional.common.feature.book.BookUiState.BookingTimes.Type.Selected
import com.hulkdx.findprofessional.common.feature.book.BookUiState.BookingTimes.Type.UnAvailable
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalAvailability
import com.hulkdx.findprofessional.common.utils.NumberFormatter.twoDigits
import com.hulkdx.findprofessional.common.utils.now
import com.hulkdx.findprofessional.common.utils.toMinutesOfDay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.number
import kotlinx.datetime.plus

class BookUseCase(
    now: LocalDate = LocalDate.now(),
) {
    private val date = MutableStateFlow(now)
    private val selectedItems = MutableStateFlow(setOf<Int>())

    fun getUiState(professional: Professional): Flow<BookUiState> =
        combine(date, selectedItems, ::Pair)
            .map { (date, selectedItems) ->
                BookUiState(
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

    fun onTimeClicked(timeId: Int) {
        selectedItems.update {
            if (it.contains(timeId)) {
                it - timeId
            } else {
                it + timeId
            }
        }
    }

    internal fun getTimes(
        professional: Professional,
        date: LocalDate,
        selectedItems: Set<Int>,
    ) =
        (0..24 * 60 step 30)
            .windowed(size = 2) { (start, end) ->
                val startTime = "${twoDigits(start / 60)}:${twoDigits(start % 60)}"
                val endTime = "${twoDigits((end / 60) % 24)}:${twoDigits(end % 60)}"

                if (selectedItems.contains(start)) {
                    return@windowed BookingTimes(
                        id = start,
                        startTime,
                        endTime,
                        Selected,
                    )
                }

                val isAvailable = professional.availability
                    .filter { it.date == date }
                    .any { isAvailabilityIncludedInTimes(it, start, end) }

                val type = if (isAvailable) Available else UnAvailable

                BookingTimes(
                    id = start,
                    startTime,
                    endTime,
                    type,
                )
            }
            .chunked(2)

    internal fun isAvailabilityIncludedInTimes(
        availability: ProfessionalAvailability,
        from: Int,
        to: Int,
    ): Boolean {
        val availabilityFrom = availability.from.toMinutesOfDay()
        val availabilityTo = availability.to.toMinutesOfDay().let { aT ->
            if (aT == 0) {
                24 * 60
            } else {
                aT
            }
        }

        return availabilityFrom <= from &&
                availabilityTo >= to
    }

    internal fun currentDay(now: LocalDate): String {
        return "${now.dayOfMonth}.${now.month.number}.${now.year}"
    }
}
