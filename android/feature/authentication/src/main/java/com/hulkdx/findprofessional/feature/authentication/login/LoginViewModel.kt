package com.hulkdx.findprofessional.feature.authentication.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.common.feature.authentication.login.LoginRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class LoginViewModel(
    private val loginRepository: LoginRepository
): ViewModel() {

    val state: StateFlow<String> = loginRepository.getFlow()
        .stateIn(viewModelScope, SharingStarted.Eagerly, "")

}
