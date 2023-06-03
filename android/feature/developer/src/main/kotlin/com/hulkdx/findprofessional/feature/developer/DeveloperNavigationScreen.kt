package com.hulkdx.findprofessional.feature.developer

import com.hulkdx.findprofessional.core.navigation.Content
import com.hulkdx.findprofessional.core.navigation.SlideNavigationScreen


class DeveloperNavigationScreen: SlideNavigationScreen() {
    override val content: Content = { DeveloperScreen() }
}
