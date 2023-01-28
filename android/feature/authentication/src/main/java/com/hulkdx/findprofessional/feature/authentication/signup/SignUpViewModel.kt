package com.hulkdx.findprofessional.feature.authentication.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.common.feature.authentication.signup.SignUpUseCase
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.RegisterRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val repository: SignUpUseCase,
) : ViewModel() {

    val email = MutableStateFlow("")
    val password = MutableStateFlow("")

    fun onSubmitClicked() = viewModelScope.launch {
        try {
            repository.register(RegisterRequest(email.value, password.value))
            // TODO: navigate to main screen
            println("onSuccess")
        } catch (e: Exception) {
            // TODO: show errors
            println("onFailure $e")
        }
    }
}
