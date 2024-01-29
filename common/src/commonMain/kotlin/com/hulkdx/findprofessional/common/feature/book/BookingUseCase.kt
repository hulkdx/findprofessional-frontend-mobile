package com.hulkdx.findprofessional.common.feature.book

import com.hulkdx.findprofessional.common.utils.NumberFormatter.twoDigits

typealias TimesType = List<List<Pair<String, String>>>

class BookingUseCase {

    fun getTimes() = BOOKING_TIMES

    companion object {
        private val BOOKING_TIMES by lazy {
            (0..24 * 60 step 30)
                .windowed(size = 2) { (start, end) ->
                    val startTime = "${twoDigits(start / 60)}:${twoDigits(start % 60)}"
                    val endTime = "${twoDigits((end / 60) % 24)}:${twoDigits(end % 60)}"
                    startTime to endTime
                }
                .chunked(2)
        }
    }
}
