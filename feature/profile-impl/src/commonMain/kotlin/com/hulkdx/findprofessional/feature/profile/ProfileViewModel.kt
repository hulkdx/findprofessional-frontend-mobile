package com.hulkdx.findprofessional.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.core.utils.StringOrRes
import com.hulkdx.findprofessional.feature.authentication.login.usecase.GetUserUseCase
import com.hulkdx.findprofessional.feature.authentication.login.usecase.LogoutUseCase
import com.hulkdx.findprofessional.feature.authentication.model.user.User
import com.hulkdx.findprofessional.feature.pro.auth.ProAuthNavigationScreen.SignUp
import com.hulkdx.findprofessional.feature.pro.model.request.SignUpProRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class ProfileViewModel(
    private val logoutUseCase: LogoutUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val navigator: Navigator,
) : ViewModel() {

    private val _error = MutableStateFlow<StringOrRes?>(null)
    val error = _error.asStateFlow()

    fun onLogoutClicked() = viewModelScope.launch {
        logoutUseCase.logout()
    }

    fun onBecomeCoachClicked() = viewModelScope.launch {
        val user = getUserUseCase.execute() as? User ?: return@launch
        navigator.navigate(
            SignUp(
                SignUpProRequest(email = user.email)
            )
        )
    }

    fun onEditProfileClicked() {
        navigator.navigate(ProfileNavigationScreen.Edit)
    }

    fun setError(error: StringOrRes?) = _error.update { error }
}
