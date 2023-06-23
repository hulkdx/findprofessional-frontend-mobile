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

    data class State(
        val route: String,
        val navOptions: NavOptions? = null,
    )
}

private fun NavigationScreen.toAndroidScreen() =
    when (this) {
        NavigationScreen.Login -> LoginNavigationScreen()
        NavigationScreen.Home -> HomeNavigationScreen()
        NavigationScreen.SignUp -> SignUpNavigationScreen()
        NavigationScreen.Developer -> DeveloperNavigationScreen()
        NavigationScreen.Splash -> SplashNavigationScreen()
    }
