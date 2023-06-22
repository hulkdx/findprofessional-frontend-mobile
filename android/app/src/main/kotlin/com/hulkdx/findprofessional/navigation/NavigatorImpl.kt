package com.hulkdx.findprofessional.navigation

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.neverEqualPolicy
import com.hulkdx.findprofessional.common.navigation.NavigationScreen
import com.hulkdx.findprofessional.common.navigation.Navigator
import com.hulkdx.findprofessional.core.navigation.AndroidNavigationScreen
import com.hulkdx.findprofessional.feature.authentication.login.LoginNavigationScreen
import com.hulkdx.findprofessional.feature.authentication.signup.SignUpNavigationScreen
import com.hulkdx.findprofessional.feature.authentication.splash.SplashNavigationScreen
import com.hulkdx.findprofessional.feature.developer.DeveloperNavigationScreen
import com.hulkdx.findprofessional.feature.home.HomeNavigationScreen

class NavigatorImpl : Navigator {

    val screenState = mutableStateOf<AndroidNavigationScreen?>(null, neverEqualPolicy())

    override fun navigate(screen: NavigationScreen) {
        val value = when (screen) {
            NavigationScreen.Login -> LoginNavigationScreen()
            NavigationScreen.Home -> HomeNavigationScreen()
            NavigationScreen.SignUp -> SignUpNavigationScreen()
            NavigationScreen.Developer -> DeveloperNavigationScreen()
            NavigationScreen.Splash -> SplashNavigationScreen()
        }
        screenState.value = value
    }
}
