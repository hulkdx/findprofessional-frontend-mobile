@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.book.time

import androidx.compose.ui.test.ExperimentalTestApi
import com.hulkdx.findprofessional.app.test.runAppUiTest
import com.hulkdx.findprofessional.app.test.utils.inMemoryApi
import com.hulkdx.findprofessional.core.features.pro.model.Professional
import com.hulkdx.findprofessional.core.features.pro.model.ProfessionalAvailability
import com.hulkdx.findprofessional.core.utils.now
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlin.test.AfterTest
import kotlin.test.Test

class BookingTimeScreenTest {

    @AfterTest
    fun tearDown() {
        inMemoryApi.resetProfessionals()
    }

    @Test
    fun performContinue() = runAppUiTest {
        launchBookingTimeScreen(this) {
            pressContinue()
        }.verify {
            bookingSummeryShown()
        }
    }

    @Test
    fun performHighlightSomeTime() = runAppUiTest {
        val availability = ProfessionalAvailability(
            id = 1,
            from = Instant.parse("2024-01-01T08:00:00Z"),
            to = Instant.parse("2024-01-01T08:30:00Z"),
        )
        val pro = listOf(
            Professional(
                1,
                "test@email.com",
                "Luba",
                "Mikaela",
                "Life coach",
                100,
                "EUR",
                "https://i.imgur.com/5Yma8Kl.jpeg",
                "5.0",
                "Former professional boxer who competed from 1985 to 2005",
                availability = listOf(availability),
                reviewSize = "0",
                reviews = listOf()
            )
        )
        inMemoryApi.setProfessionals(pro)

        launchBookingTimeScreen(this) {
            pressTime(availability)
        }.verify {
            isHighlightedTime(availability)
        }
    }
}
