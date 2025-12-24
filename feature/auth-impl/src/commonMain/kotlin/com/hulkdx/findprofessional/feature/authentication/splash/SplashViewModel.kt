package com.hulkdx.findprofessional.feature.authentication.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.feature.authentication.AuthNavigationScreen
import com.hulkdx.findprofessional.feature.authentication.login.usecase.GetUserUseCase
import com.hulkdx.findprofessional.feature.authentication.model.user.ProUser
import com.hulkdx.findprofessional.feature.authentication.model.user.User
import com.hulkdx.findprofessional.feature.home.HomeNavigationScreen
import com.hulkdx.findprofessional.feature.pro.schedule.ProScheduleNavigationScreen
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
        val dest = when (user) {
            is User -> HomeNavigationScreen.Home()
            is ProUser -> ProScheduleNavigationScreen
            null -> AuthNavigationScreen.Login
        }
        navigator.navigate(screen = dest, popTo = AuthNavigationScreen.Splash, inclusive = true)
    }
}
