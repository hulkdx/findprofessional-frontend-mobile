@file:OptIn(ExperimentalAnimationApi::class)

package com.hulkdx.findprofessional.core.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry

typealias Content = @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit

interface NavigationScreen {
    val route: String
    val arguments: List<NamedNavArgument>
    val content: Content

    val enterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?)?
    val exitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)?
    val popEnterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?)?
    val popExitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)?
}

abstract class BasicNavigationScreen: NavigationScreen {
    override val route: String
        get() = this.javaClass.name
    override val arguments: List<NamedNavArgument> = listOf()

    override val enterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?)? = null
    override val exitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)? = null
    override val popEnterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?)? = null
    override val popExitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)? = null

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
