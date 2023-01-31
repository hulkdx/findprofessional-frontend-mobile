package com.hulkdx.findprofessional.feature.authentication.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.common.feature.authentication.signup.SignUpUseCase
import com.hulkdx.findprofessional.common.feature.authentication.signup.exception.EmailExistsException
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.RegisterRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val useCase: SignUpUseCase,
) : ViewModel() {

    private val email = MutableStateFlow("")
    private val password = MutableStateFlow("")
    private val error = MutableStateFlow<Throwable?>(null)

    fun onSubmitClicked() = viewModelScope.launch {
        try {
            useCase.register(RegisterRequest(email.value, password.value))
            // TODO: navigate to main screen
            println("onSuccess")
        } catch (e: Exception) {
            error.value = e
        }
    }

    fun getEmail() = email.asStateFlow()
    fun setEmail(value: String) { email.value = value }
    fun getPassword() = password.asStateFlow()
    fun setPassword(value: String) { password.value = value }
    fun getError() = error.asStateFlow()
}
