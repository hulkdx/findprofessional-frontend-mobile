@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.login

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import com.hulkdx.findprofessional.app.test.runAppUiTest
import kotlin.test.Test

class LoginScreenTest {

    @Test
    fun myTest() = runAppUiTest {
        onNodeWithTag("LoginScreen").assertIsDisplayed()
    }
}
