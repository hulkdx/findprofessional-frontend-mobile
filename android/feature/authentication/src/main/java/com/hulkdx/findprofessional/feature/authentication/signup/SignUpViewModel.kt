package com.hulkdx.findprofessional.feature.authentication.signup

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.common.feature.authentication.signup.SignUpUseCase
import com.hulkdx.findprofessional.common.feature.authentication.signup.model.RegisterRequest
import com.hulkdx.findprofessional.common.navigation.NavigationScreen
import com.hulkdx.findprofessional.common.navigation.Navigator
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val useCase: SignUpUseCase,
    private val navigator: Navigator,
) : ViewModel() {

    val email = savedStateHandle.getStateFlow("email", "")
    val password = savedStateHandle.getStateFlow("password", "")
    val error = savedStateHandle.getStateFlow<Throwable?>("error", null)

    fun setEmail(value: String) {
        savedStateHandle["email"] = value
    }

    fun setPassword(value: String) {
        savedStateHandle["password"] = value
    }

    private fun setError(value: Throwable) {
        savedStateHandle["error"] = value
    }

    fun onSubmitClicked() = viewModelScope.launch {
        try {
            useCase.register(RegisterRequest(email.value, password.value))
            navigator.navigate(NavigationScreen.Main)
        } catch (e: Exception) {
            setError(e)
        }
    }

}
