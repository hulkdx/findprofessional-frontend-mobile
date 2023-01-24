package com.hulkdx.findprofessional.feature.authentication.signup

import com.hulkdx.findprofessional.core.navigation.BasicNavigationScreen
import com.hulkdx.findprofessional.core.navigation.Content

class SignUpNavigationScreen : BasicNavigationScreen() {
    override val content: Content = { SignUpScreen() }
}
