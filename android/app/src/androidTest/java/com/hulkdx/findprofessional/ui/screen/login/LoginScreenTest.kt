package com.hulkdx.findprofessional.ui.screen.login

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.hulkdx.findprofessional.MainActivity
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun performSignUp() {
        launchLoginScreen(composeRule) {
            pressSignUpButton()
        }.verify {
            signupScreenShown()
        }
    }

/*
    @Test
    fun performLogin() {
        launchLoginScreen(composeRule) {
            typeEmail("test@email.com")
            typePassword("test@email.com")
            pressSignInButton()
        }.verify {
            // TODO("What would be the next screen to present")
        }
    }
    */

}
