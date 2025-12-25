package com.hulkdx.findprofessional.feature.authentication

import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import kotlinx.serialization.Serializable

object AuthNavigationScreen {
    @Serializable
    data object Splash : NavigationScreen

    @Serializable
    data object Login : NavigationScreen

    @Serializable
    data object SignUp : NavigationScreen
}
