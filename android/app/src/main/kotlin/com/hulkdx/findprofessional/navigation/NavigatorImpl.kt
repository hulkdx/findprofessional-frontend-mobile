package com.hulkdx.findprofessional.navigation

import android.os.Bundle
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.neverEqualPolicy
import androidx.navigation.NavOptions
import com.hulkdx.findprofessional.common.navigation.NavigationScreen
import com.hulkdx.findprofessional.common.navigation.Navigator
import com.hulkdx.findprofessional.feature.authentication.login.LoginNavigationScreen
import com.hulkdx.findprofessional.feature.authentication.signup.SignUpNavigationScreen
import com.hulkdx.findprofessional.feature.authentication.splash.SplashNavigationScreen
import com.hulkdx.findprofessional.feature.developer.DeveloperNavigationScreen
import com.hulkdx.findprofessional.feature.home.HomeNavigationScreen
import com.hulkdx.findprofessional.feature.home.detail.HomeDetailNavigationScreen
import com.hulkdx.findprofessional.feature.home.detail.professional
import com.hulkdx.findprofessional.feature.profile.ProfileNavigationScreen

class NavigatorImpl : Navigator {

    val screenState = mutableStateOf<State?>(null, neverEqualPolicy())
    val goBack = mutableStateOf(false)
    var currentScreen: String = ""
    var currentScreenBundle: Bundle = Bundle()

    override fun navigate(screen: NavigationScreen) {
        val route = screen.toAndroidScreen()
        screenState.value = State(route)
    }

    override fun navigate(screen: NavigationScreen, popTo: NavigationScreen, inclusive: Boolean) {
        val route = screen.toAndroidScreen()
        val popToRoute = popTo.toAndroidScreen()
        val options = NavOptions.Builder()
            .setPopUpTo(popToRoute, inclusive)
            .build()
        screenState.value = State(route, options)
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

private fun NavigationScreen.toAndroidScreen(): String =
    when (this) {
        is NavigationScreen.Login -> LoginNavigationScreen().route
        is NavigationScreen.Home -> HomeNavigationScreen().route
        is NavigationScreen.HomeDetail -> HomeDetailNavigationScreen().destination(professional)
        is NavigationScreen.SignUp -> SignUpNavigationScreen().route
        is NavigationScreen.Developer -> DeveloperNavigationScreen().route
        is NavigationScreen.Splash -> SplashNavigationScreen().route
        is NavigationScreen.Profile -> ProfileNavigationScreen().route
    }

private fun String?.toNavigationScreen(bundle: Bundle) =
    when (this) {
        LoginNavigationScreen().route -> NavigationScreen.Login
        HomeNavigationScreen().route -> NavigationScreen.Home
        HomeDetailNavigationScreen().route -> NavigationScreen.HomeDetail(bundle.professional())
        SignUpNavigationScreen().route -> NavigationScreen.SignUp
        DeveloperNavigationScreen().route -> NavigationScreen.Developer
        SplashNavigationScreen().route -> NavigationScreen.Splash
        ProfileNavigationScreen().route -> NavigationScreen.Profile
        else -> throw RuntimeException("Route=$this is not defined")
    }
