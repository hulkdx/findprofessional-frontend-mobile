package com.hulkdx.findprofessional.feature.authentication.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.core.utils.StringOrRes
import com.hulkdx.findprofessional.feature.authentication.signup.model.RegisterRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val navigator: Navigator,
    private val useCase: SignUpUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        RegisterRequest(
            email = "",
            password = "",
            firstName = "",
            lastName = "",
        )
    )
    val uiState = _uiState.asStateFlow()
    private val _error = MutableStateFlow<StringOrRes?>(null)
    val error = _error.asStateFlow()

    fun onSubmitClicked() = viewModelScope.launch {
        val err = useCase.signUp(uiState.value)
        if (err == null) {
            navigator.navigate(
                NavigationScreen.Home,
                popTo = NavigationScreen.Login,
                inclusive = true
            )
        } else {
            setError(err)
        }
    }

    fun setError(error: StringOrRes?) = _error.update { error }
    fun setPassword(password: String) = _uiState.update { it.copy(password = password) }
    fun setEmail(email: String) = _uiState.update { it.copy(email = email) }
    fun setFirstName(firstName: String) = _uiState.update { it.copy(firstName = firstName) }
    fun setLastName(lastName: String) = _uiState.update { it.copy(lastName = lastName) }
}
