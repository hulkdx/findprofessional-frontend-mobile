package com.hulkdx.findprofessional.feature.authentication.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.common.feature.authentication.login.LoginRepository
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.feature.authentication.signup.SignUpNavigationScreen
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.koin.androidx.compose.get

class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val navigator: Navigator,
): ViewModel() {

    fun onSignUpClicked() {
        navigator.navigate(SignUpNavigationScreen())
    }
}
