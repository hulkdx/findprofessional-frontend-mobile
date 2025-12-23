@file:Suppress("PropertyName")

package com.hulkdx.findprofessional.libs.navigation.decompose

import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.navigate
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popWhile
import com.arkivanov.decompose.router.stack.pushToFront
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.core.platform.closeApp
import com.hulkdx.findprofessional.feature.authentication.AuthNavigationScreen
import kotlin.reflect.KClass


class DecomposeNavigator(
    private val navController: StackNavigation<NavigationScreen>,
) : Navigator {

    private var _currentScreen: NavigationScreen = AuthNavigationScreen.Splash

    override fun navigate(screen: NavigationScreen) {
        navController.pushToFront(screen)
    }

    override fun navigate(screen: NavigationScreen, popTo: NavigationScreen, inclusive: Boolean) {
        navController.navigate a@{ stack ->
            val popToIndex = stack.indexOf(popTo)
            if (popToIndex == -1) {
                return@a stack + screen
            }
            val newStack = if (inclusive) {
                stack.take(popToIndex)
            } else {
                stack.take(popToIndex + 1)
            }
            newStack - screen + screen
        }
    }

    override fun goBack() {
        navController.pop(onComplete = { isComplete ->
            if (!isComplete) {
                closeApp()
            }
        })
    }

    fun setCurrentScreen(screen: NavigationScreen) {
        _currentScreen = screen
    }

    override fun getCurrentScreen(): NavigationScreen {
        return _currentScreen
    }

    override fun goBack(screen: KClass<out NavigationScreen>) {
        navController.popWhile {
            it::class != screen
        }
    }
}
