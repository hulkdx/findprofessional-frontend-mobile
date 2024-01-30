package com.hulkdx.findprofessional.common.feature.book

import com.hulkdx.findprofessional.common.feature.book.BookUiState.BookingTimes
import com.hulkdx.findprofessional.common.feature.book.BookUiState.BookingTimes.Type.Available
import com.hulkdx.findprofessional.common.feature.book.BookUiState.BookingTimes.Type.UnAvailable
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalAvailability
import com.hulkdx.findprofessional.common.utils.NumberFormatter.twoDigits
import kotlinx.coroutines.flow.Flow

class BookUseCase {

    fun getUiState(professional: Professional): Flow<BookUiState> = TODO()

    fun getTimes(professional: Professional) =
        (0..24 * 60 step 30)
            .windowed(size = 2) { (start, end) ->
                val startTime = "${twoDigits(start / 60)}:${twoDigits(start % 60)}"
                val endTime = "${twoDigits((end / 60) % 24)}:${twoDigits(end % 60)}"

                val isAvailable =
                    professional.availability.any { isAvailabilityIncludedInTimes(it, start, end) }

                val type =
                    if (isAvailable) Available else UnAvailable

                BookingTimes(
                    startTime,
                    endTime,
                    type,
                )
            }
            .chunked(2)

    fun isAvailabilityIncludedInTimes(
        availability: ProfessionalAvailability,
        from: Int,
        to: Int,
    ): Boolean {
        return (availability.from.toSecondOfDay() / 60) <= from &&
                (availability.to.toSecondOfDay() / 60) >= to
    }

    fun dayMinusOne() {
    }

    fun dayPlusOne() {
    }
}
