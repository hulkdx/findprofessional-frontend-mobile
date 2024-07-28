package com.hulkdx.findprofessional.feature.pro.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.core.model.proauth.SignUpProUiState
import com.hulkdx.findprofessional.core.utils.StringOrRes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpProViewModel(
    initialUiState: SignUpProUiState,
    private val useCase: SignUpProUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(initialUiState)
    val uiState = _uiState.asStateFlow()
    private val _error = MutableStateFlow<StringOrRes?>(null)
    val error = _error.asStateFlow()

    fun onRegisterClicked() = viewModelScope.launch {
        val uiState = uiState.value
        if (!uiState.isConsentChecked()) {
            return@launch
        }
        val err = useCase.register(uiState)
        if (err != null) {
            setError(err)
        }
    }

    fun setError(error: StringOrRes?) {
        _error.value = error
    }

    fun setPassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun setEmail(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun setFirstName(firstName: String) {
        _uiState.update { it.copy(firstName = firstName) }
    }

    fun setLastName(lastName: String) {
        _uiState.update { it.copy(lastName = lastName) }
    }

    fun setSkypeId(value: String) {
        _uiState.update { it.copy(skypeId = value) }
    }

    fun setAboutMe(value: String) {
        _uiState.update { it.copy(aboutMe = value) }
    }

    fun setCoachType(value: String) {
        _uiState.update { it.copy(coachType = value) }
    }

    fun onPriceChanged(value: String) {
        _uiState.update { it.copy(price = value) }
    }

    fun onCurrencyChanged(value: String) {
        _uiState.update { it.copy(priceCurrency = value) }
    }

    fun onWebcamConsentCheckedChange(value: Boolean) {
        _uiState.update { it.copy(webcamConsentChecked = value) }
    }

    fun onIdConsentCheckedChange(value: Boolean) {
        _uiState.update { it.copy(idConsentChecked = value) }
    }
}
