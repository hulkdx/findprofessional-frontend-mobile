package com.hulkdx.findprofessional.common.navigation

import com.hulkdx.findprofessional.common.feature.home.model.Professional

/**
 * common NavigationScreen for both Android and iOS platform, each platform then map
 * it to platform specific code,
 *
 * Android: NavigatorImpl::navigate
 * iOS:     AppNavigationView::createScreens
 */
sealed class NavigationScreen {
    data object Splash : NavigationScreen()
    data object Login : NavigationScreen()
    data object SignUp : NavigationScreen()
    data object Home : NavigationScreen()
    data class HomeDetail(val professional: Professional) : NavigationScreen()
    data class Review(val professional: Professional) : NavigationScreen()
    data class Book(val professional: Professional) : NavigationScreen()
    data object Developer : NavigationScreen()
    data object Profile : NavigationScreen()
}
