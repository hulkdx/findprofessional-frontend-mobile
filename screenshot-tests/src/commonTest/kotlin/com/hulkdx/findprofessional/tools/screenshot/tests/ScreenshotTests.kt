package com.hulkdx.findprofessional.tools.screenshot.tests

import com.hulkdx.findprofessional.feature.authentication.login.LoginScreen
import com.hulkdx.findprofessional.feature.authentication.signup.SignUpScreen
import com.hulkdx.findprofessional.feature.authentication.splash.Splash
import com.hulkdx.findprofessional.feature.book.summery.BookingSummeryScreen
import com.hulkdx.findprofessional.feature.book.summery.BookingSummeryUiState
import com.hulkdx.findprofessional.feature.home.main.view.HomeScreen
import com.hulkdx.findprofessional.feature.profile.ProfileScreen
import com.hulkdx.findprofessional.feature.review.ReviewScreen
import kotlin.test.Test

class ScreenshotTests {
    private val className = "ScreenshotTests"

    @Test
    fun splashScreen() {
        screenShotTests(className, "splashScreen") {
            Splash()
        }
    }

    @Test
    fun loginScreen() {
        screenShotTests(className, "loginScreen") {
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
    fun signUpScreen() {
        screenShotTests(className, "signUpScreen") {
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
    fun homeScreen() {
        screenShotTests(className, "HomeScreen") {
            HomeScreen(
                professionals = professionals,
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
        screenShotTests(className, "SplashScreen paparazzi test") {
            ProfileScreen(
                onLogoutClicked = {},
                error = "",
                onErrorDismissed = {},
                onBecomeCoachClicked = {},
            )
        }
    }

    @Test
    fun reviewScreen() {
        screenShotTests(className, "reviewScreen") {
            ReviewScreen(
                reviewSize = "150",
                reviews = reviews,
                onLastItemVisible = {},
                error = "",
                onErrorDismissed = {},
            )
        }
    }

    @Test
    fun bookingSummeryScreen() {
        screenShotTests(className, "bookingSummeryScreen") {
            BookingSummeryScreen(
                error = "",
                onErrorDismissed = {},
                uiState = BookingSummeryUiState(
                    userSkypeId = "test@gmail.com", times = listOf(
                        BookingSummeryUiState.Time(
                            duration = "16:30 - 17:00",
                            date = "1.1.2024",
                            day = "Mon",
                        ),
                        BookingSummeryUiState.Time(
                            duration = "17:30 - 18:00",
                            date = "1.1.2024",
                            day = "Mon",
                        ),
                    ), totalPrices = "100 €"
                ),
            )
        }
    }
}
