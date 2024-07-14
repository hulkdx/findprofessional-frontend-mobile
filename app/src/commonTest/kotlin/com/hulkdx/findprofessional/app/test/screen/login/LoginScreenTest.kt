@file:OptIn(ExperimentalTestApi::class)

package com.hulkdx.findprofessional.app.test.screen.login

import androidx.compose.ui.test.ExperimentalTestApi
import com.hulkdx.findprofessional.app.config.api.InMemoryApi
import com.hulkdx.findprofessional.app.test.runAppUiTest
import kotlin.test.Test

class LoginScreenTest {

    //    @Test
    fun performSignUp() = runAppUiTest {
        launchLoginScreen(this) {
            pressSignUpButton()
        }.verify {
            signupScreenShown()
        }
    }

    @Test
    fun performLogin() = runAppUiTest {
        InMemoryApi.setUser("test@email.com", "somepass")

        launchLoginScreen(this) {
            typeEmail("test@email.com")
            typePassword("somepass")
            pressSignInButton()
        }.verify {
            homeScreenShown()
        }
    }
}
