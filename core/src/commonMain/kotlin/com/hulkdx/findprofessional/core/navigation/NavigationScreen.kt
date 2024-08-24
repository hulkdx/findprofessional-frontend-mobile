package com.hulkdx.findprofessional.core.navigation

import com.hulkdx.findprofessional.core.model.book.SelectedTimes
import com.hulkdx.findprofessional.core.model.pro.Professional
import com.hulkdx.findprofessional.core.model.proauth.SignUpProRequest
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
sealed class NavigationScreen {
    @Serializable data object Splash : NavigationScreen()
    @Serializable data object Login : NavigationScreen()
    @Serializable data object SignUp : NavigationScreen()
    @Serializable data object Home : NavigationScreen()
    @Serializable data class HomeDetail(val professional: Professional) : NavigationScreen()
    @Serializable data class Review(val professional: Professional) : NavigationScreen()
    @Serializable data class BookingTime(val professional: Professional) : NavigationScreen()
    @Serializable data class BookingSummery(val professional: Professional, val times: SelectedTimes) : NavigationScreen()
    @Serializable data object Developer : NavigationScreen()
    @Serializable data object Profile : NavigationScreen()

    @Serializable data class ProSignUp(val uiState: SignUpProRequest = SignUpProRequest()): NavigationScreen()
    @Serializable data object ProHome: NavigationScreen()
}
// @formatter:on
