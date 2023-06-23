package com.hulkdx.findprofessional.feature.authentication.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.common.feature.authentication.login.LoginUseCase
import kotlinx.coroutines.launch

class SplashViewModel(
    useCase: LoginUseCase,
) : ViewModel() {

    init {
        viewModelScope.launch {
            useCase.onSplashScreenLoaded()
        }
    }

}
