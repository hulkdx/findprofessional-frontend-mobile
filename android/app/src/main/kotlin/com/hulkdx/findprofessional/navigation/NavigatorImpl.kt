package com.hulkdx.findprofessional.navigation

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.neverEqualPolicy
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import com.hulkdx.findprofessional.common.navigation.NavigationScreen
import com.hulkdx.findprofessional.common.navigation.Navigator
import com.hulkdx.findprofessional.core.navigation.AndroidNavigationScreen
import com.hulkdx.findprofessional.feature.authentication.login.LoginNavigationScreen
import com.hulkdx.findprofessional.feature.authentication.signup.SignUpNavigationScreen
import com.hulkdx.findprofessional.feature.authentication.splash.SplashNavigationScreen
import com.hulkdx.findprofessional.feature.developer.DeveloperNavigationScreen
import com.hulkdx.findprofessional.feature.home.HomeNavigationScreen

class NavigatorImpl : Navigator {

    val screenState = mutableStateOf<State?>(null, neverEqualPolicy())

    override fun navigate(screen: NavigationScreen) {
        val route = screen.toAndroidScreen().route
        screenState.value = State(route)
    }

    override fun navigate(screen: NavigationScreen, popTo: NavigationScreen, inclusive: Boolean) {
        val route = screen.toAndroidScreen().route
        val popToRoute = popTo.toAndroidScreen().route
        val options = NavOptions.Builder()
            .setPopUpTo(popToRoute, inclusive)
            .build()
        screenState.value = State(route, options)
    }

    override fun getCurrentScreen(): NavigationScreen {
        return screenState.value?.route.toNavigationScreen()
    }

    data class State(
        val route: String,
        val navOptions: NavOptions? = null,
        var isNavigated: Boolean = false,
    )
}

private fun NavigationScreen.toAndroidScreen() =
    when (this) {
        is NavigationScreen.Login -> LoginNavigationScreen()
        is NavigationScreen.Home -> HomeNavigationScreen()
        is NavigationScreen.SignUp -> SignUpNavigationScreen()
        is NavigationScreen.Developer -> DeveloperNavigationScreen()
        is NavigationScreen.Splash -> SplashNavigationScreen()
    }

private fun String?.toNavigationScreen() =
    when (this) {
        LoginNavigationScreen().route -> NavigationScreen.Login
        HomeNavigationScreen().route -> NavigationScreen.Home
        SignUpNavigationScreen().route -> NavigationScreen.SignUp
        DeveloperNavigationScreen().route -> NavigationScreen.Developer
        SplashNavigationScreen().route -> NavigationScreen.Splash
        else -> throw RuntimeException("Route=$this is not defined")
    }
