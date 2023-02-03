@file:OptIn(ExperimentalAnimationApi::class)

package com.hulkdx.findprofessional.core.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NamedNavArgument

abstract class BasicNavigationScreen: AndroidNavigationScreen {
    override val route: String = this.javaClass.name
    override val arguments: List<NamedNavArgument> = listOf()

    override val enterTransition: EnterTransitionType? = null
    override val exitTransition: ExitTransitionType? = null
    override val popEnterTransition: EnterTransitionType? = null
    override val popExitTransition: ExitTransitionType? = null

    override fun hashCode(): Int {
        return route.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other is AndroidNavigationScreen) {
            return route == other.route
        }
        return super.equals(other)
    }
}
