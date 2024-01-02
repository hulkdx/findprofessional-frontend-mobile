package com.hulkdx.findprofessional.feature.developer

import app.cash.paparazzi.Paparazzi
import com.hulkdx.findprofessional.InMemoryApi
import com.hulkdx.findprofessional.feature.authentication.login.LoginScreen
import com.hulkdx.findprofessional.feature.authentication.signup.SignUpScreen
import com.hulkdx.findprofessional.feature.authentication.splash.Splash
import com.hulkdx.findprofessional.feature.home.HomeScreen
import com.hulkdx.findprofessional.feature.review.ProfileScreen
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
    fun `SplashScreen paparazzi test`() {
        paparazzi.paparazziTest {
            Splash()
        }
    }

    @Test
    fun `LoginScreen paparazzi test`() {
        paparazzi.paparazziTest {
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

    @Test
    fun `SignUpScreen paparazzi test`() {
        paparazzi.paparazziTest {
            SignUpScreen(
                firstName = "",
                onFirstNameChanged = {},
                lastName = "",
                onLastNameChanged = {},
                email = "",
                onEmailChanged = {},
                password = "",
                onPasswordChanged = {},
                onSubmitClicked = {},
                error = "",
                onErrorDismissed = {}
            )
        }
    }

    @Test
    fun `HomeScreen paparazzi test`() {
        paparazzi.paparazziTest {
            HomeScreen(
                professionals = InMemoryApi.professionals,
                error = null,
                onErrorDismissed = {},
                onSearchClick = {},
                onLikeClick = {},
                onItemClick = {},
            )
        }
    }

    @Test
    fun `ProfileScreen paparazzi test`() {
        paparazzi.paparazziTest {
            ProfileScreen(
                onLogoutClicked = {},
                error = "",
                onErrorDismissed = {},
            )
        }
    }
}
