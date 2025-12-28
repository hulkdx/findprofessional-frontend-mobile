@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.book.summery

import androidx.compose.ui.test.ExperimentalTestApi
import com.hulkdx.findprofessional.app.test.TestClockProvider
import kotlin.test.AfterTest

class BookingSummeryScreenTest {

    @AfterTest
    fun tearDown() {
        TestClockProvider.reset()
    }
}
