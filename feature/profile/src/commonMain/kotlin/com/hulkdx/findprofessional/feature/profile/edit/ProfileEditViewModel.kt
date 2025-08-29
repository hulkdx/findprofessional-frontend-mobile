package com.hulkdx.findprofessional.feature.profile.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.core.features.user.ProUser
import com.hulkdx.findprofessional.core.features.user.User
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.core.utils.PriceUtils.toPriceNumber
import com.hulkdx.findprofessional.core.utils.StringOrRes
import com.hulkdx.findprofessional.feature.authentication.login.usecase.GetUserUseCase
import com.hulkdx.findprofessional.feature.authentication.login.usecase.LogoutUseCase
import com.hulkdx.findprofessional.feature.profile.edit.usecase.UpdateProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class ProfileEditViewModel(
    getUserUseCase: GetUserUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val navigator: Navigator,
) : ViewModel() {
    private val _error: MutableStateFlow<StringOrRes?> = MutableStateFlow(null)
    val error = _error.asStateFlow()

    private val _uiState = MutableStateFlow(User())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = getUserUseCase.execute() as? User ?: User()
        }
    }

    fun onSaveButtonClicked() {
        viewModelScope.launch {
            val error = updateProfileUseCase.execute(_uiState.value)
            if (error != null) {
                _error.value = error
                return@launch
            }
            navigator.goBack()
        }
    }

    fun setFirstName(value: String) = _uiState.update { it.copy(firstName = value) }
    fun setLastName(value: String) = _uiState.update { it.copy(lastName = value) }
    fun setSkypeId(value: String) = _uiState.update { it.copy(skypeId = value) }
    fun setError(error: StringOrRes?) { _error.value = error }
}
