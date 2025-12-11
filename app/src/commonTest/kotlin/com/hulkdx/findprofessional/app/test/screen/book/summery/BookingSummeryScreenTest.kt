@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.book.summery

import androidx.compose.ui.test.ExperimentalTestApi
import com.hulkdx.findprofessional.app.test.TestClockProvider
import com.hulkdx.findprofessional.app.test.runAppUiTest
import kotlinx.datetime.LocalDate
import kotlin.test.AfterTest
import kotlin.test.Test

class BookingSummeryScreenTest {

    @AfterTest
    fun tearDown() {
        TestClockProvider.reset()
    }

    @Test
    fun performSkypeIdChange() = runAppUiTest {
        TestClockProvider.setNow(LocalDate(2024, 1, 1))

        launchBookingSummeryScreen(this) {
            pressSkypeId()
        }.verify {
            profileEditScreenShown()
        }.then {
            typeSkypeIdInProfileScreen("MySkypeId")
            pressSaveInProfileScreen()
        }.verify {
            bookingSummeryShown()
            skypeIdIs("MySkypeId")
        }
    }
}
