package com.hulkdx.findprofessional.tests.screen.signup

import com.hulkdx.findprofessional.utils.ScreenTest
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

    @Test
    fun performBackPressAfterSignUp() {
        launchSignUpScreen(composeRule) {
            pressSignUpButton()
            pressBackButton()
        }.verify {
            appIsClosed()
        }
    }
}
