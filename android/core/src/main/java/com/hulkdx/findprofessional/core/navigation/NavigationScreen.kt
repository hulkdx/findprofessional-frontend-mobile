@file:OptIn(ExperimentalAnimationApi::class)

package com.hulkdx.findprofessional.core.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentScope.SlideDirection
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry

typealias Content = @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
typealias EnterTransitionType = AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?
typealias ExitTransitionType = AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?

interface NavigationScreen {
    val route: String
    val arguments: List<NamedNavArgument>
    val content: Content

    val enterTransition: EnterTransitionType?
    val exitTransition: ExitTransitionType?
    val popEnterTransition: EnterTransitionType?
    val popExitTransition: ExitTransitionType?
}

abstract class BasicNavigationScreen: NavigationScreen {
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
        if (other is NavigationScreen) {
            return route == other.route
        }
        return super.equals(other)
    }
}

abstract class SlideNavigationScreen: BasicNavigationScreen() {
    override val enterTransition: EnterTransitionType = {
        slideIntoContainer(SlideDirection.Left, tween(300))
    }
    override val popExitTransition: ExitTransitionType = {
        slideOutOfContainer(SlideDirection.Right, tween(300))
    }
}
