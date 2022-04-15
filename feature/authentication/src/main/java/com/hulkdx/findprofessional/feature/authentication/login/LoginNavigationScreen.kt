package com.hulkdx.findprofessional.feature.authentication.login

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import com.hulkdx.findprofessional.core.navigation.NavigationScreen


class LoginNavigationScreen: NavigationScreen {
    override val route: String
        get() = this.javaClass.name
    override val arguments: List<NamedNavArgument>
        get() = listOf()
    override val content: @Composable (NavBackStackEntry) -> Unit
        get() = {
            LoginScreen()
        }
}
