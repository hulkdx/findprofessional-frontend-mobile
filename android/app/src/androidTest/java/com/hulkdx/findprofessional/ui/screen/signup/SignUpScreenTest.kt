package com.hulkdx.findprofessional.ui.screen.signup

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.hulkdx.findprofessional.MainActivity
import com.hulkdx.findprofessional.common.config.api.InMemoryApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

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
        launchSignUpScreen(composeRule) {
            pressSignUpButton()
        }.verify {
            mainScreenShown()
        }
    }
}
