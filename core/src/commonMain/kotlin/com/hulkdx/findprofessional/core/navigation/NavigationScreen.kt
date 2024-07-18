package com.hulkdx.findprofessional.core.navigation

import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
sealed class NavigationScreen {
    @Serializable
    data object Splash : NavigationScreen()
    @Serializable
    data object Login : NavigationScreen()
    @Serializable
    data object SignUp : NavigationScreen()
    @Serializable
    data object SignUpPro : NavigationScreen()
    @Serializable
    data object Home : NavigationScreen()
//    data class HomeDetail(val professional: Professional) : NavigationScreen()
//    data class Review(val professional: Professional) : NavigationScreen()
//    data class BookingTime(val professional: Professional) : NavigationScreen()
//    data class BookingSummery(val professional: Professional, val times: SelectedTimes) : NavigationScreen()
    @Serializable
    data object Developer : NavigationScreen()
    @Serializable
    data object Profile : NavigationScreen()
}
// @formatter:on
