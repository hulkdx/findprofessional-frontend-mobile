package com.hulkdx.findprofessional.feature.pro.auth

import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.feature.pro.model.request.SignUpProRequest
import kotlinx.serialization.Serializable

object ProAuthNavigationScreen {
    @Serializable
    data class SignUp(val uiState: SignUpProRequest = SignUpProRequest()) : NavigationScreen()
}
