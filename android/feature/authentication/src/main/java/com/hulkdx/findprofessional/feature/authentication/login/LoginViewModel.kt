package com.hulkdx.findprofessional.feature.authentication.login

import androidx.lifecycle.ViewModel
import com.hulkdx.findprofessional.common.feature.authentication.login.LoginUseCase
import com.hulkdx.findprofessional.common.navigation.NavigationScreen
import com.hulkdx.findprofessional.common.navigation.Navigator

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val navigator: Navigator,
): ViewModel() {

    fun onSignUpClicked() {
        navigator.navigate(NavigationScreen.SignUp)
    }

    fun onSignInClicked() {

    }
}
