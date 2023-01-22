package com.hulkdx.findprofessional.feature.authentication.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.common.feature.authentication.signup.SignUpUseCase
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val repository: SignUpUseCase,
) : ViewModel() {

    fun onSubmitClicked() {
        viewModelScope.launch {
            val response = repository.greeting()
            println(response)
        }
    }
}
