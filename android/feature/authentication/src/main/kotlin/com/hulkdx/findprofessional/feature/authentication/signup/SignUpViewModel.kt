package com.hulkdx.findprofessional.feature.authentication.signup

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.common.feature.authentication.signup.SignUpUseCase
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.LoginRequest
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val useCase: SignUpUseCase,
) : ViewModel() {
    val email = savedStateHandle.getStateFlow("email", "")
    val password = savedStateHandle.getStateFlow("password", "")
    val error = savedStateHandle.getStateFlow<StringDesc?>("error", null)

    fun onSubmitClicked() = viewModelScope.launch {
        val err = useCase.onSubmitClicked(LoginRequest(email.value, password.value))
        if (err != null) {
            setError(err)
        }
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
