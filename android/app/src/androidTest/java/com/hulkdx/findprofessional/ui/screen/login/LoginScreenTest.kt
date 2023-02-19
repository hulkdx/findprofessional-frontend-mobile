package com.hulkdx.findprofessional.ui.screen.login

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.hulkdx.findprofessional.MainActivity
import com.hulkdx.findprofessional.common.config.api.InMemoryApi
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.AuthRequest
import com.hulkdx.findprofessional.utils.UiTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    val rule = UiTestRule()

    @Before
    fun setUp() {
        InMemoryApi.loadKoinModules()
    }

    @After
    fun tearDown() {
        InMemoryApi.unloadKoinModules()
    }

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
        InMemoryApi.user = AuthRequest("test@email.com", "somepass")

        launchLoginScreen(composeRule) {
            typeEmail("test@email.com")
            typePassword("somepass")
            pressSignInButton()
        }.verify {
            mainScreenShown()
        }
    }

}
