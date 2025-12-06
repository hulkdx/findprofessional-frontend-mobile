package com.hulkdx.findprofessional.core.navigation

import kotlin.reflect.KClass

interface Navigator {
    fun navigate(screen: NavigationScreen)
    fun navigate(screen: NavigationScreen, popTo: NavigationScreen, inclusive: Boolean)
    fun goBack()
    fun goBack(screen: KClass<out NavigationScreen>)
    fun getCurrentScreen(): NavigationScreen
}
