package com.hulkdx.findprofessional.feature.profile

import com.hulkdx.findprofessional.core.navigation.Content
import com.hulkdx.findprofessional.core.navigation.SlideNavigationScreen


class ProfileNavigationScreen : SlideNavigationScreen() {
    override val content: Content = { ProfileScreen() }
}
