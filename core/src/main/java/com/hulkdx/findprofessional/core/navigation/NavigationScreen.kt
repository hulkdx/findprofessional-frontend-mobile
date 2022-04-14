package com.hulkdx.findprofessional.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry

interface NavigationScreen {
    val route: String
    val arguments: List<NamedNavArgument>
    val content: @Composable (NavBackStackEntry) -> Unit
}
