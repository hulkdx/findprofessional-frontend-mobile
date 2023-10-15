package com.hulkdx.findprofessional.feature.profile

import com.hulkdx.findprofessional.feature.navigation.screen.Content
import com.hulkdx.findprofessional.feature.navigation.screen.SlideNavigationScreen


class ProfileNavigationScreen : SlideNavigationScreen() {
    override val content: Content = { ProfileScreen() }
}
