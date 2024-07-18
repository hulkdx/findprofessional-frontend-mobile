@file:Suppress("PropertyName")

package com.hulkdx.findprofessional.libs.navigation.decompose

import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.navigate
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.hulkdx.findprofessional.core.config.closeApp
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.navigation.Navigator


class DecomposeNavigator(
    private val navController: StackNavigation<NavigationScreen>,
) : Navigator {

    var _currentScreen: NavigationScreen = NavigationScreen.Splash

    override fun navigate(screen: NavigationScreen) {
        navController.push(screen)
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
            newStack + screen
        }
    }

    override fun goBack() {
        navController.pop(onComplete = { isComplete ->
            if (!isComplete) {
                closeApp()
            }
        })
    }

    override fun getCurrentScreen() = _currentScreen
}

