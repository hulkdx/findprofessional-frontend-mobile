package com.hulkdx.findprofessional.core.navigation

interface Navigator {
    fun navigate(screen: NavigationScreen)
    fun navigate(screen: NavigationScreen, popTo: NavigationScreen, inclusive: Boolean)
    fun goBack()
    fun getCurrentScreen(): NavigationScreen
}
