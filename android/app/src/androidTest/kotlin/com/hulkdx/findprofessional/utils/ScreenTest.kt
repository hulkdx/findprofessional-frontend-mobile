package com.hulkdx.findprofessional.utils

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.hulkdx.findprofessional.MainActivity
import org.junit.Rule

open class ScreenTest {
    @get:Rule(order = 1)
    val inMemoryApiRule = InMemoryApiRule()

    @get:Rule(order = 2)
    val koinTestRule = KoinTestRule()

    @get:Rule(order = 3)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @get:Rule(order = 4)
    val screenshotOnFailureRule = ScreenshotOnFailureRule()
}
