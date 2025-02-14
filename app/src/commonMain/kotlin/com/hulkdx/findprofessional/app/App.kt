package com.hulkdx.findprofessional.app

import androidx.compose.runtime.Composable
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.ui.theme.AppTheme
import com.hulkdx.findprofessional.feature.authentication.login.LoginScreen
import com.hulkdx.findprofessional.feature.authentication.signup.SignUpScreen
import com.hulkdx.findprofessional.feature.authentication.splash.SplashScreen
import com.hulkdx.findprofessional.feature.book.summery.BookingSummeryScreen
import com.hulkdx.findprofessional.feature.book.time.BookingTimeScreen
import com.hulkdx.findprofessional.feature.developer.DeveloperScreen
import com.hulkdx.findprofessional.feature.home.detail.HomeDetailScreen
import com.hulkdx.findprofessional.feature.home.main.view.HomeScreen
import com.hulkdx.findprofessional.feature.pro.auth.signup.view.SignUpProScreen
import com.hulkdx.findprofessional.feature.pro.availability.detail.ProAvailabilityDetailScreen
import com.hulkdx.findprofessional.feature.pro.availability.main.ProAvailabilityScreen
import com.hulkdx.findprofessional.feature.pro.profile.ProProfileScreen
import com.hulkdx.findprofessional.feature.pro.profile.edit.EditProProfileScreen
import com.hulkdx.findprofessional.feature.pro.schedule.ProScheduleScreen
import com.hulkdx.findprofessional.feature.profile.ProfileScreen
import com.hulkdx.findprofessional.feature.profile.edit.ProfileEditScreen
import com.hulkdx.findprofessional.feature.review.ReviewScreen
import com.hulkdx.findprofessional.libs.navigation.decompose.RootComponent
import com.hulkdx.findprofessional.libs.navigation.decompose.RootContent

@Composable
fun App(component: RootComponent) {
    AppTheme {
        RootContent(component) { RenderScreen(it) }
    }
}

@Composable
private fun RenderScreen(screen: NavigationScreen) {
    // @formatter:off
    when (screen) {
        is NavigationScreen.Developer -> DeveloperScreen()
        is NavigationScreen.Splash -> SplashScreen()
        is NavigationScreen.Login -> LoginScreen()
        is NavigationScreen.SignUp -> SignUpScreen()
        is NavigationScreen.Home -> HomeScreen()
        is NavigationScreen.HomeDetail -> HomeDetailScreen(screen.professional)
        is NavigationScreen.Review -> ReviewScreen(screen.professional)
        is NavigationScreen.Profile -> ProfileScreen()
        is NavigationScreen.ProfileEdit -> ProfileEditScreen()
        is NavigationScreen.BookingTime -> BookingTimeScreen(screen.professional)
        is NavigationScreen.BookingSummery -> BookingSummeryScreen(screen.professional, screen.times)

        is NavigationScreen.ProSignUp -> SignUpProScreen(screen.uiState)
        is NavigationScreen.ProSchedule -> ProScheduleScreen()
        is NavigationScreen.ProAvailability -> ProAvailabilityScreen()
        is NavigationScreen.ProAvailabilityDetail -> ProAvailabilityDetailScreen(screen.date)
        is NavigationScreen.ProProfile -> ProProfileScreen()
        is NavigationScreen.EditProProfile -> EditProProfileScreen()
    }
    // @formatter:on
}
