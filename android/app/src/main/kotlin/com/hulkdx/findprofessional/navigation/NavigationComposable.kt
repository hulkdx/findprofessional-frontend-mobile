package com.hulkdx.findprofessional.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hulkdx.findprofessional.feature.navigation.screen.AndroidNavigationScreen
import com.hulkdx.findprofessional.feature.authentication.splash.SplashNavigationScreen
import org.koin.compose.getKoin
import org.koin.compose.koinInject

@Composable
fun NavigationComposable() {
    val navController = rememberNavController()
    SetupNavigation(navController)
    CreateScreens(navController)
}

@Composable
private fun CreateScreens(navController: NavHostController) {
    val startDestination = SplashNavigationScreen().route
    val navigationScreens: List<AndroidNavigationScreen> = getKoin().getAll()

    NavHost(navController = navController, startDestination = startDestination) {
        for (navigationScreen in navigationScreens) {
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

@Composable
private fun SetupNavigation(navController: NavHostController) {
    val navigator: NavigatorImpl = koinInject()

    SetupNavigationNavigate(navController, navigator)
    SetupNavigationCurrentScreen(navController, navigator)
    SetupNavigationGoBack(navigator, navController)
}

@Composable
private fun SetupNavigationNavigate(
    navController: NavHostController,
    navigator: NavigatorImpl,
) {
    val screenState by remember { navigator.screenState }

    screenState?.apply {
        if (isNavigated) {
            return@apply
        }
        isNavigated = true
        navController.navigate(route, navOptions)
    }
}

@Composable
private fun SetupNavigationGoBack(
    navigator: NavigatorImpl,
    navController: NavHostController,
) {
    val goBack by remember { navigator.goBack }

    if (goBack) {
        navigator.goBack.value = false
        navController.popBackStack()
    }
}

@Composable
private fun SetupNavigationCurrentScreen(
    navController: NavHostController,
    navigator: NavigatorImpl,
) {
    val navStack by navController.currentBackStackEntryAsState()
    navigator.currentScreen = navStack?.destination?.route ?: return
    navigator.currentScreenBundle = navStack?.arguments ?: return
}
