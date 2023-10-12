package com.hulkdx.findprofessional.feature.authentication.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.common.feature.authentication.login.LoginUseCase
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.AuthRequest
import com.hulkdx.findprofessional.core.utils.getStateFlowWrapper
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.launch

class LoginViewModel(
    savedStateHandle: SavedStateHandle,
    private val loginUseCase: LoginUseCase,
) : ViewModel() {
    val email by savedStateHandle.getStateFlowWrapper("")
    val password by savedStateHandle.getStateFlowWrapper("")
    val error by savedStateHandle.getStateFlowWrapper<StringDesc?>(null)

    fun onSignUpClicked() {
        loginUseCase.onSignUpClicked()
    }

    fun onSignInClicked() = viewModelScope.launch {
        val err = loginUseCase.onSignInClicked(AuthRequest(email.value, password.value))
        if (err != null) {
            error.set(err)
        }
    }

    fun onDevClicked() {
        loginUseCase.onDevClicked()
    }
}
