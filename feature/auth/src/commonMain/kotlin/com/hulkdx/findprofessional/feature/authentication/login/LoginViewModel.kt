package com.hulkdx.findprofessional.feature.authentication.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.core.utils.StringOrRes
import com.hulkdx.findprofessional.feature.authentication.login.model.LoginRequest
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
        navigator.navigate(NavigationScreen.SignUp)
    }

    fun onSignInClicked() = viewModelScope.launch {
        val err = loginUseCase.login(LoginRequest(email.value, password.value))
        if (err == null) {
            navigator.navigate(NavigationScreen.Home, popTo = NavigationScreen.Login, inclusive = true)
        } else {
            setError(err)
        }
    }

    fun onDevClicked() {
        navigator.navigate(NavigationScreen.Developer)
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
