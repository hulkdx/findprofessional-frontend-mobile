package com.hulkdx.findprofessional.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hulkdx.findprofessional.core.di.startDestinationRouteQualifier
import org.koin.androidx.compose.koinInjectAll
import org.koin.compose.koinInject

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    SetupNavHost(
        navController = navController,
        startDestinationRoute = koinInject(startDestinationRouteQualifier),
        screens = koinInjectAll(),
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
