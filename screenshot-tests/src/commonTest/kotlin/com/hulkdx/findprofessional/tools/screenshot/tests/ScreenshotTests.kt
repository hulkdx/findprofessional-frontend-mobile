package com.hulkdx.findprofessional.tools.screenshot.tests

import com.hulkdx.findprofessional.core.model.proauth.SignUpProRequest
import com.hulkdx.findprofessional.core.model.user.ProUser
import com.hulkdx.findprofessional.feature.authentication.login.LoginScreen
import com.hulkdx.findprofessional.feature.authentication.signup.SignUpScreen
import com.hulkdx.findprofessional.feature.authentication.splash.Splash
import com.hulkdx.findprofessional.feature.book.summery.BookingSummeryScreen
import com.hulkdx.findprofessional.feature.book.summery.BookingSummeryUiState
import com.hulkdx.findprofessional.feature.book.time.BookingTimeScreen
import com.hulkdx.findprofessional.feature.home.detail.HomeDetailScreen
import com.hulkdx.findprofessional.feature.home.detail.availability.AvailabilityData
import com.hulkdx.findprofessional.feature.home.main.view.HomeScreen
import com.hulkdx.findprofessional.feature.pro.auth.signup.SignUpProScreenStep1
import com.hulkdx.findprofessional.feature.pro.auth.signup.SignUpProScreenStep2
import com.hulkdx.findprofessional.feature.pro.profile.ProProfileScreen
import com.hulkdx.findprofessional.feature.pro.profile.edit.EditProProfileScreen
import com.hulkdx.findprofessional.feature.profile.ProfileScreen
import com.hulkdx.findprofessional.feature.review.ReviewScreen
import kotlinx.datetime.LocalDate
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
                password = "",
                showDeveloper = false,
                "", {}, {}, {}, {}, {}, {}, {}
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
    fun homeDetailScreen() {
        screenShotTests(className, "homeDetailScreen") {
            HomeDetailScreen(
                professional = professionals[0],
                availability = AvailabilityData(
                    "January 2022",
                    5,
                    31,
                    LocalDate(2022, 1, 6),
                    listOf(
                        LocalDate(2022, 1, 6),
                        LocalDate(2022, 1, 7),
                        LocalDate(2022, 1, 12),
                    )
                ),
                {}, {}, {}, {}, {}, {}
            )
        }
    }

    @Test
    fun profileScreen() {
        screenShotTests(className, "profileScreen") {
            ProfileScreen({}, {}, "", {})
        }
    }

    @Test
    fun reviewScreen() {
        screenShotTests(className, "reviewScreen") {
            ReviewScreen(
                reviewSize = "150",
                reviews = reviews,
                {}, "", {},
            )
        }
    }

    @Test
    fun bookingTimeScreen() {
        screenShotTests(className, "bookingTimeScreen") {
            BookingTimeScreen(bookingTime, {}, {}, {}, "", {}, {})
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

    @Test
    fun signUpProScreenStep1() {
        screenShotTests(className, "SignUpProScreenStep1") {
            SignUpProScreenStep1(
                uiState = SignUpProRequest(),
                {}, {}, {}, "", {},
            )
        }
    }

    @Test
    fun signUpProScreenStep2() {
        screenShotTests(className, "SignUpProScreenStep2") {
            SignUpProScreenStep2(
                uiState = SignUpProRequest(),
                {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, "", {},
            )
        }
    }

    @Test
    fun proProfileScreen() {
        screenShotTests(className, "proProfileScreen") {
            ProProfileScreen(
                "Mosh Asphalt",
                "",
                {}, {}, "", {},
            )
        }
    }

    @Test
    fun proEditProfileScreen() {
        screenShotTests(className, "proEditProfileScreen") {
            EditProProfileScreen(
                ProUser(
                    firstName = "Mosh",
                    lastName = "Asphalt",
                    email = "mosh.ashphalt@gmail.com",
                    coachType = "Life Coach",
                    description = "Hi, I'm a life coach",
                    priceNumber = 500,
                    skypeId = "mosh.asphalt",
                ),
                "", {}, {}, {}, {}, {}, {}, {}, {}, {}
            )
        }
    }
}
