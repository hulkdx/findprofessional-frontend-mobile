package com.hulkdx.findprofessional.core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry

typealias Content = @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
typealias EnterTransitionType = AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?
typealias ExitTransitionType = AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?

abstract class NavHostScreen {
    val route: String = this::class.simpleName ?: ""
    val arguments: List<NamedNavArgument> = listOf()
    abstract val content: Content

    val enterTransition: EnterTransitionType? = null
    val exitTransition: ExitTransitionType? = null
    val popEnterTransition: EnterTransitionType? = null
    val popExitTransition: ExitTransitionType? = null

    override fun hashCode(): Int {
        return route.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other is NavHostScreen) {
            return route == other.route
        }
        return super.equals(other)
    }
}
