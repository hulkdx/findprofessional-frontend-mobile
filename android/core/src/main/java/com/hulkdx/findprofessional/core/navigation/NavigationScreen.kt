package com.hulkdx.findprofessional.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry

interface NavigationScreen {
    val route: String
    val arguments: List<NamedNavArgument>
    val content: @Composable (NavBackStackEntry) -> Unit
}

abstract class BasicNavigationScreen(
    override val content: @Composable (NavBackStackEntry) -> Unit,
) : NavigationScreen {
    override val route: String
        get() = this.javaClass.name
    override val arguments: List<NamedNavArgument> = listOf()

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
