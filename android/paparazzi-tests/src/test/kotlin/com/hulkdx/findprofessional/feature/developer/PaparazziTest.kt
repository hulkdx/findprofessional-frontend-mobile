package com.hulkdx.findprofessional.feature.developer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.hulkdx.findprofessional.InMemoryApi
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.feature.authentication.login.LoginScreen
import com.hulkdx.findprofessional.feature.authentication.signup.SignUpScreen
import com.hulkdx.findprofessional.feature.authentication.splash.Splash
import com.hulkdx.findprofessional.feature.home.HomeScreen
import com.hulkdx.findprofessional.feature.home.detail.HomeDetailScreen
import com.hulkdx.findprofessional.feature.profile.ProfileScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PaparazziTest {
    @get:Rule
    val paparazzi = Paparazzi()

    @get:Rule
    val paparazzi2 = Paparazzi(
        deviceConfig = DeviceConfig.NEXUS_5.copy(screenHeight = 5000)
    )

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
    fun `HomeDetailScreen paparazzi test`() {
        paparazzi2.paparazziTest {
            HomeDetailScreen(
                professional = InMemoryApi.professionals[0],
                // @formatter:off
                availability = listOf(
                    listOf("", "Thu\n19", "Fri\n20", "Sat\n21", "Sun\n22", "Mon\n23", "Tue\n24"),
                    listOf("00-04", "0", "0", "0", "0", "0", "0"),
                    listOf("04-08", "0", "1", "0", "0", "0", "0"),
                    listOf("08-12", "0", "0", "2", "0", "0", "0"),
                    listOf("12-16", "0", "0", "0", "3", "0", "0"),
                    listOf("16-20", "0", "0", "0", "0", "0", "0"),
                    listOf("20-24", "0", "0", "0", "0", "4", "0"),
                ),
                // @formatter:on
                {},
                {},
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

    private fun Paparazzi.paparazziTest(content: @Composable () -> Unit) {
        snapshot {
            CompositionLocalProvider(LocalInspectionMode provides true) {
                AppTheme {
                    content()
                }
            }
        }
    }
}
