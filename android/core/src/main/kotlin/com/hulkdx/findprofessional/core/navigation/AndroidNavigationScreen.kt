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

interface AndroidNavigationScreen {
    val route: String
    val arguments: List<NamedNavArgument>
    val content: Content

    val enterTransition: EnterTransitionType?
    val exitTransition: ExitTransitionType?
    val popEnterTransition: EnterTransitionType?
    val popExitTransition: ExitTransitionType?
}
