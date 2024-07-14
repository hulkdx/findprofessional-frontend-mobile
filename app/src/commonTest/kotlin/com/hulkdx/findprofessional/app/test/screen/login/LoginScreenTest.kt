@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.login

import androidx.compose.ui.test.ExperimentalTestApi
import com.hulkdx.findprofessional.app.test.runAppUiTest
import kotlin.test.Test

class LoginScreenTest {

    @Test
    fun performSignUp() = runAppUiTest {
        launchLoginScreen(this) {
            pressSignUpButton()
        }.verify {
            signupScreenShown()
        }
    }
}
