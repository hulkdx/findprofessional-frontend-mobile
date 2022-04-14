package com.hulkdx.findprofessional.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.feature.authentication.login.LoginNavigationScreen
import org.koin.androidx.compose.getKoin
import org.koin.androidx.compose.get

@Composable
fun NavigationComposable() {
    val navController = rememberNavController()
    val navigationScreens: List<NavigationScreen> = getKoin().getAll()

    val navigator: NavigatorImpl = get()
    navigator.setNavController(navController)

    NavHost(navController = navController, startDestination = LoginNavigationScreen().route) {
        for (navigationScreen in navigationScreens) {
            composable(
                route = navigationScreen.route,
                arguments = navigationScreen.arguments,
                content = navigationScreen.content,
            )
        }
    }
}
