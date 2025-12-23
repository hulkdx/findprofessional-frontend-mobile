package com.hulkdx.findprofessional.feature.pro.profile.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.feature.authentication.model.user.ProUser
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.core.utils.PriceUtils.toPriceNumber
import com.hulkdx.findprofessional.core.utils.StringOrRes
import com.hulkdx.findprofessional.feature.pro.auth.signup.usecase.GetProUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditProProfileViewModel(
    private val getProUserUseCase: GetProUserUseCase,
    private val saveProUserUseCase: SaveProUserUseCase,
    private val navigator: Navigator,
) : ViewModel() {

    private val _error: MutableStateFlow<StringOrRes?> = MutableStateFlow(null)
    val error = _error.asStateFlow()

    private val _uiState = MutableStateFlow(ProUser())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = getProUserUseCase.getUser() ?: ProUser()
        }
    }

    fun onSaveButtonClicked() {
        viewModelScope.launch {
            val error = saveProUserUseCase.save(_uiState.value)
            if (error != null) {
                _error.value = error
                return@launch
            }
            navigator.navigate(NavigationScreen.ProProfile)
        }
    }

    fun setFirstName(value: String) = _uiState.update { it.copy(firstName = value) }
    fun setLastName(value: String) = _uiState.update { it.copy(lastName = value) }
    fun setSkypeId(value: String) = _uiState.update { it.copy(skypeId = value) }
    fun setEmail(value: String) = _uiState.update { it.copy(email = value) }
    fun setCoachType(value: String) = _uiState.update { it.copy(coachType = value) }
    fun setAboutMe(value: String) = _uiState.update { it.copy(description = value) }
    fun setPrice(value: String) = _uiState.update { it.copy(priceNumber = toPriceNumber(value)) }
    fun setError(error: StringOrRes?) {
        _error.value = error
    }
}
