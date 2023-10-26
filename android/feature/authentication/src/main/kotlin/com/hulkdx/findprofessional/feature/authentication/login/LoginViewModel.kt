package com.hulkdx.findprofessional.feature.authentication.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.common.feature.authentication.login.LoginUseCase
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.AuthRequest
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.launch

class LoginViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val loginUseCase: LoginUseCase,
) : ViewModel() {
    val email = savedStateHandle.getStateFlow("email", "")
    val password = savedStateHandle.getStateFlow("password", "")
    val error = savedStateHandle.getStateFlow<StringDesc?>("error", null)

    fun onSignUpClicked() {
        loginUseCase.onSignUpClicked()
    }

    fun onSignInClicked() = viewModelScope.launch {
        val err = loginUseCase.onSignInClicked(AuthRequest(email.value, password.value))
        if (err != null) {
            setError(err)
        }
    }

    fun onDevClicked() {
        loginUseCase.onDevClicked()
    }

    fun setError(error: StringDesc?) {
        savedStateHandle["error"] = error
    }

    fun setPassword(password: String) {
        savedStateHandle["password"] = password
    }

    fun setEmail(email: String) {
        savedStateHandle["email"] = email
    }
}
