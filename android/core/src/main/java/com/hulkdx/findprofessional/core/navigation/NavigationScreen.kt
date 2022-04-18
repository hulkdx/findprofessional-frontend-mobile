package com.hulkdx.findprofessional.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry

abstract class NavigationScreen {
    abstract val route: String
    abstract val arguments: List<NamedNavArgument>
    abstract val content: @Composable (NavBackStackEntry) -> Unit

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
