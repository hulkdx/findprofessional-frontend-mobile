package com.hulkdx.findprofessional.feature.authentication.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.common.feature.authentication.signup.SignUpRepository
import com.hulkdx.findprofessional.core.navigation.Navigator
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val repository: SignUpRepository,
) : ViewModel() {

    fun onSubmitClicked() {
        viewModelScope.launch {
            val response = repository.greeting()
            println(response)
        }
    }
}
