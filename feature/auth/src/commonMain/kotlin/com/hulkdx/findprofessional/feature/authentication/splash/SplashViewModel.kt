package com.hulkdx.findprofessional.feature.authentication.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.feature.authentication.login.usecase.GetUserUseCase
import kotlinx.coroutines.launch

class SplashViewModel(
    private val navigator: Navigator,
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {

    init {
        onLoaded()
    }

    private fun onLoaded() = viewModelScope.launch {
        val user = getUserUseCase.execute()
        val dest = if (user == null) {
            NavigationScreen.Login
        } else {
            NavigationScreen.Home
        }
        navigator.navigate(screen = dest, popTo = NavigationScreen.Splash, inclusive = true)
    }
}
