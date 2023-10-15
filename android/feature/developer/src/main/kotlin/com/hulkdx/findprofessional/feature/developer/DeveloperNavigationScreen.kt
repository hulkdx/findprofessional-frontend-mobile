package com.hulkdx.findprofessional.feature.developer

import com.hulkdx.findprofessional.feature.navigation.screen.Content
import com.hulkdx.findprofessional.feature.navigation.screen.SlideNavigationScreen


class DeveloperNavigationScreen: SlideNavigationScreen() {
    override val content: Content = { DeveloperScreen() }
}
