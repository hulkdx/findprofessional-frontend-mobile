package com.hulkdx.findprofessional.feature.developer

import app.cash.paparazzi.Paparazzi
import com.hulkdx.findprofessional.InMemoryApi
import com.hulkdx.findprofessional.common.feature.authentication.model.User
import com.hulkdx.findprofessional.common.feature.book.summery.BookingSummeryUiState
import com.hulkdx.findprofessional.common.feature.book.summery.BookingSummeryUiState.Time
import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalReview
import com.hulkdx.findprofessional.feature.authentication.login.LoginScreen
import com.hulkdx.findprofessional.feature.authentication.signup.SignUpScreen
import com.hulkdx.findprofessional.feature.authentication.splash.Splash
import com.hulkdx.findprofessional.feature.book.summery.BookingSummeryScreen
import com.hulkdx.findprofessional.feature.home.HomeScreen
import com.hulkdx.findprofessional.feature.profile.ProfileScreen
import com.hulkdx.findprofessional.feature.review.ReviewScreen
import kotlinx.datetime.Clock
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
                onBecomeCoachClicked = {},
            )
        }
    }

    @Test
    fun `ReviewScreen paparazzi test`() {
        paparazzi.paparazziTest {
            ReviewScreen(
                reviewSize = "150",
                reviews = listOf(
                    ProfessionalReview(
                        id = 0,
                        user = User(
                            profileImage = null,
                            firstName = "Stefan",
                            lastName = "Holman",
                            email = "",
                        ),
                        rate = 5,
                        contentText = "He was a great coach for me!",
                        createdAt = Clock.System.now(),
                        updatedAt = Clock.System.now(),
                    ),
                    ProfessionalReview(
                        id = 1,
                        user = User(
                            profileImage = null,
                            firstName = "Bill",
                            lastName = "Gates",
                            email = "",
                        ),
                        rate = 1,
                        contentText = "I love it, but it was bad.",
                        createdAt = Clock.System.now(),
                        updatedAt = Clock.System.now(),
                    )
                ),
                onLastItemVisible = {},
                error = "",
                onErrorDismissed = {},
            )
        }
    }

    @Test
    fun `BookingSummeryScreen paparazzi test`() {
        paparazzi.paparazziTest {
            BookingSummeryScreen(
                error = "",
                onErrorDismissed = {},
                uiState = BookingSummeryUiState(
                    userSkypeId = "test@gmail.com", times = listOf(
                        Time(
                            duration = "16:30 - 17:00",
                            date = "1.1.2024",
                            day = "Mon",
                        ),
                        Time(
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
