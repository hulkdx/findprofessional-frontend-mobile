package com.hulkdx.findprofessional.feature.authentication.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.core.platform.isDebug
import com.hulkdx.findprofessional.core.utils.StringOrRes
import com.hulkdx.findprofessional.feature.authentication.AuthNavigationScreen
import com.hulkdx.findprofessional.feature.authentication.login.model.LoginRequest
import com.hulkdx.findprofessional.feature.authentication.model.user.ProUser
import com.hulkdx.findprofessional.feature.authentication.model.user.User
import com.hulkdx.findprofessional.feature.developer.DeveloperNavigationScreen
import com.hulkdx.findprofessional.feature.home.HomeNavigationScreen
import com.hulkdx.findprofessional.feature.pro.schedule.ProScheduleNavigationScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val navigator: Navigator,
) : ViewModel() {
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()
    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()
    private val _error = MutableStateFlow<StringOrRes?>(null)
    val error = _error.asStateFlow()

    fun onSignUpClicked() {
        navigator.navigate(AuthNavigationScreen.SignUp)
    }

    fun onSignInClicked() = viewModelScope.launch {
        val (err, userData) = loginUseCase.login(LoginRequest(email.value, password.value))
        if (err != null) {
            setError(err)
            return@launch
        }

        val screen = when (userData?.user) {
            is ProUser -> ProScheduleNavigationScreen
            is User -> HomeNavigationScreen.Home()
            null -> return@launch
        }
        navigator.navigate(screen, popTo = AuthNavigationScreen.Login, inclusive = true)
    }

    fun onDevClicked() {
        if (isDebug()) {
            navigator.navigate(DeveloperNavigationScreen)
        }
    }

    fun onForgotPasswordClicked() {
        // TODO:
    }

    fun setError(error: StringOrRes?) {
        _error.value = error
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setEmail(email: String) {
        _email.value = email
    }
}
