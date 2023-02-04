package com.hulkdx.findprofessional.common.feature.authentication.login

import com.hulkdx.findprofessional.common.navigation.NavigationScreen
import com.hulkdx.findprofessional.common.navigation.Navigator


class LoginUseCase(
    private val navigator: Navigator,
) {
    fun onSignUpClicked() {
        navigator.navigate(NavigationScreen.SignUp)
    }

    fun onSignInClicked() {
    }
}
