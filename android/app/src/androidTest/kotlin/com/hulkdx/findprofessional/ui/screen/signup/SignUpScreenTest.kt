package com.hulkdx.findprofessional.ui.screen.signup

import com.hulkdx.findprofessional.ui.screen.ScreenTest
import org.junit.Test

class SignUpScreenTest : ScreenTest() {

    @Test
    fun performSignUp() {
        launchSignUpScreen(composeRule) {
            pressSignUpButton()
        }.verify {
            homeScreenShown()
        }
    }
}
