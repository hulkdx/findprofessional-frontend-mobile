@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.book.confirmation

import androidx.compose.ui.test.ExperimentalTestApi
import com.hulkdx.findprofessional.app.test.runAppUiTest
import kotlin.test.Test

class BookingConfirmationScreenTest {

    @Test
    fun closeNavigatesToHome() = runAppUiTest {
        launchBookingConfirmationScreen(this) {
            pressClose()
        }.verify {
            homeScreenShown()
        }
    }
}
