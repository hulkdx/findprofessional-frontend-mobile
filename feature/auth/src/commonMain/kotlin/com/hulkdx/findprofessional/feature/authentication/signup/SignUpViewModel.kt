package com.hulkdx.findprofessional.feature.authentication.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.core.utils.StringOrRes
import com.hulkdx.findprofessional.feature.authentication.signup.model.RegisterRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val navigator: Navigator,
    private val useCase: SignUpUseCase,
) : ViewModel() {
    val uiState = MutableStateFlow(
        RegisterRequest(
            email = "",
            password = "",
            firstName = "",
            lastName = "",
        )
    )
    val error = MutableStateFlow<StringOrRes?>(null)

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

    fun setError(error: StringOrRes?) {
        this.error.value = error
    }

    fun setPassword(password: String) {
        uiState.update { it.copy(password = password) }
    }

    fun setEmail(email: String) {
        uiState.update { it.copy(email = email) }
    }

    fun setFirstName(firstName: String) {
        uiState.update { it.copy(firstName = firstName) }
    }

    fun setLastName(lastName: String) {
        uiState.update { it.copy(lastName = lastName) }
    }
}
