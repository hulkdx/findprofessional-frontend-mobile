package com.hulkdx.findprofessional.feature.pro.profile.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.core.utils.PriceUtils.toPriceNumber
import com.hulkdx.findprofessional.core.features.user.ProUser
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.navigation.Navigator
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
            _error.value = saveProUserUseCase.save(_uiState.value)
            navigator.navigate(NavigationScreen.ProProfile)
        }
    }

    private fun updateState(update: ProUser.() -> ProUser) = _uiState.update { it.update() }
    fun setFirstName(value: String) = updateState { copy(firstName = value) }
    fun setLastName(value: String) = updateState { copy(lastName = value) }
    fun setSkypeId(value: String) = updateState { copy(skypeId = value) }
    fun setEmail(value: String) = updateState { copy(email = value) }
    fun setCoachType(value: String) = updateState { copy(coachType = value) }
    fun setAboutMe(value: String) = updateState { copy(description = value) }
    fun setPrice(value: String) = updateState { copy(priceNumber = toPriceNumber(value)) }
    fun setError(error: StringOrRes?) { _error.value = error }
}
