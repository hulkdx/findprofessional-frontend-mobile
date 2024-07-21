package com.hulkdx.findprofessional.app

import androidx.compose.runtime.Composable
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.feature.authentication.login.LoginScreen
import com.hulkdx.findprofessional.feature.authentication.signup.SignUpScreen
import com.hulkdx.findprofessional.feature.authentication.splash.SplashScreen
import com.hulkdx.findprofessional.feature.developer.DeveloperScreen
import com.hulkdx.findprofessional.feature.home.detail.view.HomeDetailScreen
import com.hulkdx.findprofessional.feature.home.main.view.HomeScreen
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
    when (screen) {
        is NavigationScreen.Developer -> DeveloperScreen()
        is NavigationScreen.Splash -> SplashScreen()
        is NavigationScreen.Login -> LoginScreen()
        is NavigationScreen.SignUp -> SignUpScreen()
        is NavigationScreen.Home -> HomeScreen()
        is NavigationScreen.HomeDetail -> HomeDetailScreen(screen.professional)
        else -> TODO()
    }
}
