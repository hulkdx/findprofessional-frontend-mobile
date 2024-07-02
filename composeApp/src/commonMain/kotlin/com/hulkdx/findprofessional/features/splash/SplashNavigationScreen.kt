package com.hulkdx.findprofessional.features.splash

import com.hulkdx.findprofessional.core.navigation.Content
import com.hulkdx.findprofessional.core.navigation.NavHostScreen
import com.hulkdx.findprofessional.features.splash.ui.SplashScreen

class SplashNavigationScreen : NavHostScreen() {
    override val content: Content = { SplashScreen() }
}
