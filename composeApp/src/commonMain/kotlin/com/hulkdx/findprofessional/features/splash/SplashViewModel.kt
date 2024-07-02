package com.hulkdx.findprofessional.features.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    init {
        viewModelScope.launch {
//            useCase.onSplashScreenLoaded()
        }
    }

}
