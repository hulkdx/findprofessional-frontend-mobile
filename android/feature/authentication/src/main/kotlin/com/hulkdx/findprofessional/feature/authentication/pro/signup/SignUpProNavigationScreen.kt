package com.hulkdx.findprofessional.feature.authentication.pro.signup

import com.hulkdx.findprofessional.feature.navigation.screen.Content
import com.hulkdx.findprofessional.feature.navigation.screen.SlideNavigationScreen

class SignUpProNavigationScreen : SlideNavigationScreen() {
    override val content: Content = { SignUpProScreen() }
}
