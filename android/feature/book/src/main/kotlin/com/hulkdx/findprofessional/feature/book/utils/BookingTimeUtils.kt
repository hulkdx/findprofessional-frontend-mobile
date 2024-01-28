package com.hulkdx.findprofessional.feature.book.utils


val BOOKING_TIMES by lazy {
    (0..24 * 60 step 30)
        .windowed(size = 2) { (start, end) ->
            val startTime = String.format("%02d:%02d", start / 60, start % 60)
            val endTime = String.format("%02d:%02d", (end / 60) % 24, end % 60)
            startTime to endTime
        }
        .chunked(2)
}
