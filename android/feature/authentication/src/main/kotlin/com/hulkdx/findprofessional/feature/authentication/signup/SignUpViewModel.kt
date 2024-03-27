package com.hulkdx.findprofessional.feature.authentication.signup

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.common.feature.authentication.signup.SignUpUseCase
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.RegisterRequest
import com.hulkdx.findprofessional.common.utils.StringOrRes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

private const val KEY_STATE = "key_ui_state"
private const val KEY_ERROR = "key_error"

class SignUpViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val useCase: SignUpUseCase,
) : ViewModel() {
    val uiState = savedStateHandle.getStateFlow(
        KEY_STATE,
        RegisterRequest(
            email = "",
            password = "",
            firstName = "",
            lastName = "",
        )
    )
    val error = MutableStateFlow<StringOrRes?>(null)

    fun onSubmitClicked() = viewModelScope.launch {
        val err = useCase.onSubmitClicked(uiState.value)
        if (err != null) {
            setError(err)
        }
    }

    fun setError(error: StringOrRes?) {
        this.error.value = error
    }

    fun setPassword(password: String) {
        savedStateHandle[KEY_STATE] = uiState.value.copy(password = password)
    }

    fun setEmail(email: String) {
        savedStateHandle[KEY_STATE] = uiState.value.copy(email = email)
    }

    fun setFirstName(firstName: String) {
        savedStateHandle[KEY_STATE] = uiState.value.copy(firstName = firstName)
    }

    fun setLastName(lastName: String) {
        savedStateHandle[KEY_STATE] = uiState.value.copy(lastName = lastName)
    }
}
