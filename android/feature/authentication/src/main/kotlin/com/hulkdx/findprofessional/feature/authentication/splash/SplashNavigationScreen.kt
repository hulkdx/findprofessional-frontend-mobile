package com.hulkdx.findprofessional.feature.authentication.splash

import com.hulkdx.findprofessional.feature.navigation.screen.BasicNavigationScreen
import com.hulkdx.findprofessional.feature.navigation.screen.Content

class SplashNavigationScreen : BasicNavigationScreen() {
    override val content: Content = { SplashScreen() }
}
