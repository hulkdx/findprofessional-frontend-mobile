package com.hulkdx.findprofessional.tests.screen.login

import com.hulkdx.findprofessional.InMemoryApi
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.LoginRequest
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.RegisterRequest
import com.hulkdx.findprofessional.utils.ScreenTest
import org.junit.Test

class LoginScreenTest : ScreenTest() {

    @Test
    fun performSignUp() {
        launchLoginScreen(composeRule) {
            pressSignUpButton()
        }.verify {
            signupScreenShown()
        }
    }

    @Test
    fun performLogin() {
        InMemoryApi.setUser("test@email.com", "somepass")

        launchLoginScreen(composeRule) {
            typeEmail("test@email.com")
            typePassword("somepass")
            pressSignInButton()
        }.verify {
            homeScreenShown()
        }
    }
}
