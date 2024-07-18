@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.signup

import androidx.compose.ui.test.ExperimentalTestApi
import com.hulkdx.findprofessional.app.test.runAppUiTest
import kotlin.test.Test

class SignUpScreenTest {

    @Test
    fun performSignUp() = runAppUiTest {
        launchSignUpScreen(this) {
            pressSignUpButton()
        }.verify {
            homeScreenShown()
        }
    }
}
