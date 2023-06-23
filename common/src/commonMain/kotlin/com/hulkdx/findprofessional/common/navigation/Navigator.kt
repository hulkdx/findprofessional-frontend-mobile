package com.hulkdx.findprofessional.common.navigation

interface Navigator {
    fun navigate(screen: NavigationScreen)
    fun navigate(screen: NavigationScreen, popTo: NavigationScreen, inclusive: Boolean)
}
