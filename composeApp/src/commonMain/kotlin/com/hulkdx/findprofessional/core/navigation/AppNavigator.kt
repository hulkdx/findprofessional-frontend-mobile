package com.hulkdx.findprofessional.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hulkdx.findprofessional.core.di.startDestinationRouteQualifier
import org.koin.compose.getKoin

@Composable
fun AppNavigator() {
    val koin = getKoin()
    val navController = rememberNavController()

    SetupNavHost(
        navController = navController,
        startDestinationRoute = koin.get(startDestinationRouteQualifier),
        screens = koin.getAll(),
    )
}

@Composable
private fun SetupNavHost(
    navController: NavHostController,
    startDestinationRoute: String,
    screens: List<NavHostScreen>,
) {
    NavHost(navController = navController, startDestination = startDestinationRoute) {
        for (navigationScreen in screens) {
            composable(
                route = navigationScreen.route,
                arguments = navigationScreen.arguments,
                content = navigationScreen.content,
                enterTransition = navigationScreen.enterTransition,
                exitTransition = navigationScreen.exitTransition,
                popEnterTransition = navigationScreen.popEnterTransition,
                popExitTransition = navigationScreen.popExitTransition,
            )
        }
    }
}
