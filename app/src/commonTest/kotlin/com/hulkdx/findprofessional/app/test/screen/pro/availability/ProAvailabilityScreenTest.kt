@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.pro.availability

import androidx.compose.ui.test.ExperimentalTestApi
import com.hulkdx.findprofessional.app.test.TestClockProvider
import com.hulkdx.findprofessional.app.test.runAppUiTest
import com.hulkdx.findprofessional.app.test.utils.inMemoryApi
import com.hulkdx.findprofessional.libs.common.tests.createAvailabilities
import kotlinx.datetime.LocalDate
import kotlin.test.AfterTest
import kotlin.test.Test

class ProAvailabilityScreenTest {
    @AfterTest
    fun tearDown() {
        TestClockProvider.reset()
    }

    @Test
    fun performSelectDate() = runAppUiTest {
        launchProAvailabilityScreen(this) {
            selectDate("1")
        }.verify {
            proAvailabilityDetailShown()
        }
    }

    @Test
    fun assertHighlightedText() = runAppUiTest {
        TestClockProvider.setNow(LocalDate(2025, 1, 1))

        val availability = createAvailabilities(
            LocalDate(2025, 1, 2)
        )

        launchProAvailabilityScreen(this, availability)
            .verify {
                assertDateHighlighted("2")
            }
    }

}
