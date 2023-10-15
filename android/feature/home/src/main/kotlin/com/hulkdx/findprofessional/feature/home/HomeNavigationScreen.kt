package com.hulkdx.findprofessional.feature.home

import com.hulkdx.findprofessional.feature.navigation.screen.BasicNavigationScreen
import com.hulkdx.findprofessional.feature.navigation.screen.Content

class HomeNavigationScreen : BasicNavigationScreen() {
    override val content: Content = { HomeScreen() }
}
