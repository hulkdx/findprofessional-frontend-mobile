package com.hulkdx.findprofessional.feature.authentication.login

import com.hulkdx.findprofessional.core.navigation.BasicNavigationScreen
import com.hulkdx.findprofessional.core.navigation.Content


class LoginNavigationScreen : BasicNavigationScreen() {
    override val content: Content = { LoginScreen() }
}
