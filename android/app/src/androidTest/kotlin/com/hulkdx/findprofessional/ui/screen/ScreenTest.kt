package com.hulkdx.findprofessional.ui.screen

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.hulkdx.findprofessional.MainActivity
import com.hulkdx.findprofessional.common.config.api.InMemoryApi
import com.hulkdx.findprofessional.utils.UiTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule

open class ScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @get:Rule(order = 1)
    val rule = UiTestRule(composeRule)

    @Before
    open fun setUp() {
        InMemoryApi.loadKoinModules()
    }

    @After
    open fun tearDown() {
        InMemoryApi.unloadKoinModules()
    }
}
