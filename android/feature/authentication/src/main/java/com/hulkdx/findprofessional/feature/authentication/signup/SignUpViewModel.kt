package com.hulkdx.findprofessional.feature.authentication.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.common.feature.authentication.signup.SignUpUseCase
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.RegisterRequest
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val repository: SignUpUseCase,
) : ViewModel() {

    fun onSubmitClicked() = viewModelScope.launch {
        runCatching {
            val email = "user3@gmail.comu"
            val password = "12332423adfs"
            repository.register(RegisterRequest(email, password))
        }.onSuccess {
            println("onSuccess $it")
        }.onFailure {
            println("onFailure $it")
        }
    }
}
