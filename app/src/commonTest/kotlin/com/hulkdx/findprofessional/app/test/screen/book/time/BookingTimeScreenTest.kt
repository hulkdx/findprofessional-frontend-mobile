@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.book.time

import androidx.compose.ui.test.ExperimentalTestApi
import com.hulkdx.findprofessional.app.test.TestClockProvider
import com.hulkdx.findprofessional.app.test.runAppUiTest
import com.hulkdx.findprofessional.app.test.utils.inMemoryApi
import com.hulkdx.findprofessional.feature.pro.model.ProfessionalAvailability
import kotlinx.datetime.LocalDate
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.time.Instant

class BookingTimeScreenTest {

    @AfterTest
    fun tearDown() {
        inMemoryApi.resetProfessionals()
        TestClockProvider.reset()
    }

    @Test
    fun performContinue() = runAppUiTest {
        TestClockProvider.setNow(LocalDate(2024, 1, 1))
        val availability = ProfessionalAvailability(
            id = 1,
            from = Instant.parse("2024-01-01T08:00:00Z"),
            to = Instant.parse("2024-01-01T08:30:00Z"),
        )
        inMemoryApi.setProAvailability(listOf(availability))

        launchBookingTimeScreen(this) {
            pressTime(availability)
            pressContinue()
        }.verify {
            bookingSummeryShown()
        }
    }

    @Test
    fun performHighlightSomeTime() = runAppUiTest {
        TestClockProvider.setNow(LocalDate(2024, 1, 1))

        val availability = ProfessionalAvailability(
            id = 1,
            from = Instant.parse("2024-01-01T08:00:00Z"),
            to = Instant.parse("2024-01-01T08:30:00Z"),
        )
        inMemoryApi.setProAvailability(listOf(availability))

        launchBookingTimeScreen(this) {
            pressTime(availability)
        }.verify {
            isHighlightedTime(availability)
        }
    }
}
