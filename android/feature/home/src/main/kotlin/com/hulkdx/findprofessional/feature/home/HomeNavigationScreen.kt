package com.hulkdx.findprofessional.feature.home

import com.hulkdx.findprofessional.feature.navigation.screen.Content
import com.hulkdx.findprofessional.feature.navigation.screen.SlideNavigationScreen

class HomeNavigationScreen : SlideNavigationScreen() {
    override val content: Content = { HomeScreen() }
}
