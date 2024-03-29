package com.hulkdx.findprofessional.navigation

import android.os.Bundle
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavOptions
import com.hulkdx.findprofessional.common.navigation.NavigationScreen
import com.hulkdx.findprofessional.common.navigation.Navigator
import com.hulkdx.findprofessional.feature.authentication.login.LoginNavigationScreen
import com.hulkdx.findprofessional.feature.authentication.pro.signup.SignUpProNavigationScreen
import com.hulkdx.findprofessional.feature.authentication.signup.SignUpNavigationScreen
import com.hulkdx.findprofessional.feature.authentication.splash.SplashNavigationScreen
import com.hulkdx.findprofessional.feature.book.summery.BookingSummeryNavigationScreen
import com.hulkdx.findprofessional.feature.book.time.BookingTimeNavigationScreen
import com.hulkdx.findprofessional.feature.developer.DeveloperNavigationScreen
import com.hulkdx.findprofessional.feature.home.HomeNavigationScreen
import com.hulkdx.findprofessional.feature.home.detail.HomeDetailNavigationScreen
import com.hulkdx.findprofessional.feature.profile.ProfileNavigationScreen
import com.hulkdx.findprofessional.feature.review.ReviewNavigationScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class NavigatorImpl : Navigator {

    val screenState = MutableStateFlow<State?>(null)
    val goBack = mutableStateOf(false)
    var currentScreen: String = ""
    var currentScreenBundle: Bundle = Bundle()

    override fun navigate(screen: NavigationScreen) {
        val route = screen.toAndroidScreen()
        screenState.update { State(route) }
    }

    override fun navigate(screen: NavigationScreen, popTo: NavigationScreen, inclusive: Boolean) {
        val route = screen.toAndroidScreen()
        val popToRoute = popTo.toAndroidScreen()
        val options = NavOptions.Builder()
            .setPopUpTo(popToRoute, inclusive)
            .build()
        screenState.update { State(route, options) }
    }

    override fun goBack() {
        goBack.value = true
    }

    override fun getCurrentScreen(): NavigationScreen {
        return currentScreen.toNavigationScreen(currentScreenBundle)
    }

    data class State(
        val route: String,
        val navOptions: NavOptions? = null,
        var isNavigated: Boolean = false,
    )
}

//@formatter:off

private fun NavigationScreen.toAndroidScreen(): String =
    when (this) {
        is NavigationScreen.Login -> LoginNavigationScreen().route
        is NavigationScreen.Home -> HomeNavigationScreen().route
        is NavigationScreen.HomeDetail -> HomeDetailNavigationScreen().destination(professional)
        is NavigationScreen.SignUp -> SignUpNavigationScreen().route
        is NavigationScreen.SignUpPro -> SignUpProNavigationScreen().route
        is NavigationScreen.Developer -> DeveloperNavigationScreen().route
        is NavigationScreen.Splash -> SplashNavigationScreen().route
        is NavigationScreen.Profile -> ProfileNavigationScreen().route
        is NavigationScreen.Review -> ReviewNavigationScreen().destination(professional)
        is NavigationScreen.BookingTime -> BookingTimeNavigationScreen().destination(professional)
        is NavigationScreen.BookingSummery -> BookingSummeryNavigationScreen().destination(professional, times)
    }

private fun String?.toNavigationScreen(bundle: Bundle) =
    when (this) {
        LoginNavigationScreen().route -> NavigationScreen.Login
        HomeNavigationScreen().route -> NavigationScreen.Home
        HomeDetailNavigationScreen().route -> NavigationScreen.HomeDetail(HomeDetailNavigationScreen.professional(bundle))
        SignUpNavigationScreen().route -> NavigationScreen.SignUp
        DeveloperNavigationScreen().route -> NavigationScreen.Developer
        SplashNavigationScreen().route -> NavigationScreen.Splash
        ProfileNavigationScreen().route -> NavigationScreen.Profile
        ReviewNavigationScreen().route -> NavigationScreen.Review(ReviewNavigationScreen.professional(bundle))
        BookingTimeNavigationScreen().route -> NavigationScreen.BookingTime(BookingTimeNavigationScreen.professional(bundle))
        BookingSummeryNavigationScreen().route -> NavigationScreen.BookingSummery(BookingSummeryNavigationScreen.professional(bundle), BookingSummeryNavigationScreen.time(bundle))
        SignUpProNavigationScreen().route -> NavigationScreen.SignUpPro
        else -> throw RuntimeException("Route=$this is not defined")
    }

//@formatter:on
