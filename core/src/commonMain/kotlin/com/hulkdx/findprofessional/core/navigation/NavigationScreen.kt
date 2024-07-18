package com.hulkdx.findprofessional.core.navigation

import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
sealed class NavigationScreen {
    data object Splash : NavigationScreen()
    data object Login : NavigationScreen()
    data object SignUp : NavigationScreen()
    data object SignUpPro : NavigationScreen()
    data object Home : NavigationScreen()
//    data class HomeDetail(val professional: Professional) : NavigationScreen()
//    data class Review(val professional: Professional) : NavigationScreen()
//    data class BookingTime(val professional: Professional) : NavigationScreen()
//    data class BookingSummery(val professional: Professional, val times: SelectedTimes) : NavigationScreen()
    data object Developer : NavigationScreen()
    data object Profile : NavigationScreen()
}
// @formatter:on
