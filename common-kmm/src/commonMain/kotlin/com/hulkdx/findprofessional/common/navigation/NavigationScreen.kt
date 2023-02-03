package com.hulkdx.findprofessional.common.navigation

sealed class NavigationScreen {
    object Login: NavigationScreen()
    object SignUp: NavigationScreen()
    object Main: NavigationScreen()
}
