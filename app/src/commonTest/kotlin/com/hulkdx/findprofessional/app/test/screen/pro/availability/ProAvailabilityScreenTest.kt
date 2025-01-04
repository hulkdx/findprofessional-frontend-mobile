@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.pro.availability

import androidx.compose.ui.test.ExperimentalTestApi
import com.hulkdx.findprofessional.app.test.runAppUiTest
import kotlin.test.Test

class ProAvailabilityScreenTest {
    @Test
    fun performSelectDate() = runAppUiTest {
        launchProAvailabilityScreen(this) {
            selectFirstDate()
        }.verify {
            proAvailabilityDetailShown()
        }
    }
}
