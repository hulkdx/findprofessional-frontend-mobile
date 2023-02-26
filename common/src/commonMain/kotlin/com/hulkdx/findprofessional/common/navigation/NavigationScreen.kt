package com.hulkdx.findprofessional.common.navigation

/**
 * common NavigationScreen for both Android and iOS platform, each platform then map
 * it to platform specific code,
 *
 * Android: NavigatorImpl::navigate
 * iOS:     AppNavigationView::createScreens
 */
sealed class NavigationScreen {
    object Login : NavigationScreen()
    object SignUp : NavigationScreen()
    object Main : NavigationScreen()
}
