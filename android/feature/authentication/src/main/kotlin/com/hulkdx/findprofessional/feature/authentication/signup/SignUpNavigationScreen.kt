package com.hulkdx.findprofessional.feature.authentication.signup

import com.hulkdx.findprofessional.feature.navigation.screen.Content
import com.hulkdx.findprofessional.feature.navigation.screen.SlideNavigationScreen

class SignUpNavigationScreen : SlideNavigationScreen() {
    override val content: Content = { SignUpScreen() }
}
