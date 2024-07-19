package com.hulkdx.findprofessional.app

import androidx.compose.runtime.Composable
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.feature.authentication.login.LoginScreen
import com.hulkdx.findprofessional.feature.authentication.signup.SignUpScreen
import com.hulkdx.findprofessional.feature.authentication.splash.SplashScreen
import com.hulkdx.findprofessional.feature.home.view.HomeScreen
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
        NavigationScreen.Splash -> SplashScreen()
        NavigationScreen.Login -> LoginScreen()
        NavigationScreen.SignUp -> SignUpScreen()
        NavigationScreen.Home -> HomeScreen()
        else -> TODO()
    }
}
