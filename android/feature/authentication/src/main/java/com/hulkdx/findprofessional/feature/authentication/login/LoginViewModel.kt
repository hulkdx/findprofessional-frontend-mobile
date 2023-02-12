package com.hulkdx.findprofessional.feature.authentication.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.common.feature.authentication.login.LoginUseCase
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.AuthRequest
import com.hulkdx.findprofessional.common.navigation.NavigationScreen
import com.hulkdx.findprofessional.common.navigation.Navigator
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.launch

class LoginViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val loginUseCase: LoginUseCase,
): ViewModel() {
    val email = savedStateHandle.getStateFlow("email", "")
    val password = savedStateHandle.getStateFlow("password", "")
    val error = savedStateHandle.getStateFlow<StringDesc?>("error", null)

    fun setEmail(value: String) {
        savedStateHandle["email"] = value
    }

    fun setPassword(value: String) {
        savedStateHandle["password"] = value
    }

    private fun setError(value: StringDesc) {
        savedStateHandle["error"] = value
    }

    fun onSignUpClicked() {
        loginUseCase.onSignUpClicked()
    }

    fun onSignInClicked() = viewModelScope.launch {
        loginUseCase.onSignInClicked(AuthRequest(email.value, password.value))
    }
}
