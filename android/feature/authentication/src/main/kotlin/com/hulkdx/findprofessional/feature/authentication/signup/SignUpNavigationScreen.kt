package com.hulkdx.findprofessional.feature.authentication.signup

import com.hulkdx.findprofessional.core.navigation.Content
import com.hulkdx.findprofessional.core.navigation.SlideNavigationScreen

class SignUpNavigationScreen : SlideNavigationScreen() {
    override val content: Content = { SignUpScreen() }
}
