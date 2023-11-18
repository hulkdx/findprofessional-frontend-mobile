package com.hulkdx.findprofessional.feature.developer

import app.cash.paparazzi.Paparazzi
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.feature.authentication.login.LoginScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class PaparazziTest {
    @get:Rule
    val paparazzi = Paparazzi()

    @Before
    fun setup() {
    }

    @Test
    fun `LoginScreen paparazzi test`() {
        paparazzi.snapshot {
            AppTheme {
                LoginScreen(
                    email = "",
                    onEmailChanged = {},
                    password = "",
                    onPasswordChanged = {},
                    onSignInClicked = {},
                    onSignUpClicked = {},
                    error = "",
                    onErrorDismissed = {},
                    onForgotPasswordClicked = {},
                    onDevClicked = {},
                    showDeveloper = true,
                )
            }
        }
    }

}
