package com.hulkdx.findprofessional.feature.authentication.signup

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import com.hulkdx.findprofessional.core.navigation.NavigationScreen

class SignUpNavigationScreen: NavigationScreen() {
    override val route: String
        get() = this.javaClass.name
    override val arguments: List<NamedNavArgument>
        get() = listOf()
    override val content: @Composable (NavBackStackEntry) -> Unit
        get() = {
            SignUpScreen()
        }
}
