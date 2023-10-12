package com.hulkdx.findprofessional.common.navigation

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
    data object HomeDetail : NavigationScreen()
    data object Developer : NavigationScreen()
    data object Profile : NavigationScreen()
}
