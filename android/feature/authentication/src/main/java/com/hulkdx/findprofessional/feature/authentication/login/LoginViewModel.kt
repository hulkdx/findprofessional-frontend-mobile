package com.hulkdx.findprofessional.feature.authentication.login

import androidx.lifecycle.ViewModel
import com.hulkdx.findprofessional.common.feature.authentication.login.LoginUseCase
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.feature.authentication.signup.SignUpNavigationScreen

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val navigator: Navigator,
): ViewModel() {

    fun onSignUpClicked() {
        navigator.navigate(SignUpNavigationScreen())
    }

    fun onSignInClicked() {

    }
}
