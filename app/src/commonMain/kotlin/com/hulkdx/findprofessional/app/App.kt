package com.hulkdx.findprofessional.app

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.feature.authentication.splash.SplashScreen
import com.hulkdx.findprofessional.libs.navigation.decompose.ComponentContext
import com.hulkdx.findprofessional.libs.navigation.decompose.RootContent

@Composable
fun App(componentContext: ComponentContext) {
    RootContent(componentContext) { RenderScreen(it) }
}

@Composable
private fun RenderScreen(screen: NavigationScreen) {
    when (screen) {
        NavigationScreen.Splash -> SplashScreen()
        NavigationScreen.Login -> Text("login")
        else -> TODO()
    }
}
