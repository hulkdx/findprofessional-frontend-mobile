package com.hulkdx.findprofessional.feature.authentication.signup

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.common.feature.authentication.signup.SignUpUseCase
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.AuthRequest
import com.hulkdx.findprofessional.core.utils.getStateFlowWrapper
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.launch

class SignUpViewModel(
    savedStateHandle: SavedStateHandle,
    private val useCase: SignUpUseCase,
) : ViewModel() {
    val email by savedStateHandle.getStateFlowWrapper("")
    val password by savedStateHandle.getStateFlowWrapper("")
    val error by savedStateHandle.getStateFlowWrapper<StringDesc?>(null)

    fun onSubmitClicked() = viewModelScope.launch {
        val err = useCase.register(AuthRequest(email.value, password.value))
        if (err != null) {
            error.set(err)
        }
    }

}
