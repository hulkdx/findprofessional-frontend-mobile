@file:OptIn(ExperimentalAnimationApi::class)

package com.hulkdx.findprofessional.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.hulkdx.findprofessional.core.navigation.AndroidNavigationScreen
import com.hulkdx.findprofessional.feature.authentication.splash.SplashNavigationScreen
import org.koin.compose.getKoin
import org.koin.compose.koinInject

@Composable
fun NavigationComposable() {
    val navController = rememberAnimatedNavController()
    SetupNavigation(navController)
    CreateScreens(navController)
}

@Composable
private fun CreateScreens(navController: NavHostController) {
    val startDestination = SplashNavigationScreen().route
    val navigationScreens: List<AndroidNavigationScreen> = getKoin().getAll()

    AnimatedNavHost(navController = navController, startDestination = startDestination) {
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
private fun SetupNavigationCurrentScreen(
    navController: NavHostController,
    navigator: NavigatorImpl,
) {
    val navStack by navController.currentBackStackEntryAsState()
    val currentScreen = navStack?.destination?.route
    if (currentScreen != null) {
        navigator.currentScreen = currentScreen
    }
}
