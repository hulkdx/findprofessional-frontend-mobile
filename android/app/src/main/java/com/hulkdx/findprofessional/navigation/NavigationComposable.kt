package com.hulkdx.findprofessional.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.feature.authentication.login.LoginNavigationScreen
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getKoin

@Composable
fun NavigationComposable() {
    val navController = rememberNavController()
    CreateScreens(navController)
    SetupNavigation(navController)
}

@Composable
private fun CreateScreens(navController: NavHostController) {
    val startDestination = LoginNavigationScreen().route
    val navigationScreens: List<NavigationScreen> = getKoin().getAll()

    NavHost(navController = navController, startDestination = startDestination) {
        for (navigationScreen in navigationScreens) {
            composable(
                route = navigationScreen.route,
                arguments = navigationScreen.arguments,
                content = navigationScreen.content,
            )
        }
    }
}

@Composable
private fun SetupNavigation(navController: NavHostController) {
    val navigator: NavigatorImpl = get()

    navigator.screenState.value?.apply {
        navController.navigate(route)
    }
}
