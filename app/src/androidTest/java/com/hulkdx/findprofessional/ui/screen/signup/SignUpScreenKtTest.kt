package com.hulkdx.findprofessional.ui.screen.signup

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.hulkdx.findprofessional.MainActivity
import org.junit.Rule
import org.junit.Test

class SignUpScreenKtTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun performSignUp() {
        launchSignUpScreen(composeRule) {
            typeEmail("test@email.com")
            typePassword("test@email.com")
            pressSignUpButton()
        }.verify {
            // TODO("What would be the next screen to present")
        }
    }

}
