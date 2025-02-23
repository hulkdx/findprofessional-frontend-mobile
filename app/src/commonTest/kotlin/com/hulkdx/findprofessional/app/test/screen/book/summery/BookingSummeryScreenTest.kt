@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.book.summery

import androidx.compose.ui.test.ExperimentalTestApi
import com.hulkdx.findprofessional.app.test.runAppUiTest
import kotlin.test.AfterTest
import kotlin.test.Test

class BookingSummeryScreenTest {

    @AfterTest
    fun tearDown() {
    }

    @Test
    fun performSkypeIdChange() = runAppUiTest {
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
