package com.hulkdx.findprofessional.feature.profile

import com.hulkdx.findprofessional.feature.navigation.screen.BasicNavigationScreen
import com.hulkdx.findprofessional.feature.navigation.screen.Content


class ProfileNavigationScreen : BasicNavigationScreen() {
    override val content: Content = { ProfileScreen() }
}
