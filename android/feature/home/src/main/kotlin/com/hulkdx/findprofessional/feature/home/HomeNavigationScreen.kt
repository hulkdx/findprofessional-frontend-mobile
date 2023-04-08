package com.hulkdx.findprofessional.feature.home

import com.hulkdx.findprofessional.core.navigation.Content
import com.hulkdx.findprofessional.core.navigation.SlideNavigationScreen

class HomeNavigationScreen : SlideNavigationScreen() {
    override val content: Content = { HomeScreen() }
}
