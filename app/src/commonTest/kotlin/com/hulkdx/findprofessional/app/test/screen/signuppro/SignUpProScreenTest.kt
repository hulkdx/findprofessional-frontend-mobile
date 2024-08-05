@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.signuppro

import androidx.compose.ui.test.ExperimentalTestApi
import com.hulkdx.findprofessional.app.test.runAppUiTest
import kotlin.test.Test

class SignUpProScreenTest {
    @Test
    fun performNext() = runAppUiTest {
        launchSignUpProScreen(this) {
            pressNextButton()
        }.verify {
            signUpProStepTwoScreenShown()
        }
    }

    @Test
    fun performRegister() = runAppUiTest {
        launchSignUpProScreen(this) {
            pressRegisterButton()
        }.verify {
            // TODO: what should happens?
        }
    }
}
