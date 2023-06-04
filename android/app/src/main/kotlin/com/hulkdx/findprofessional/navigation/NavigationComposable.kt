@file:OptIn(ExperimentalAnimationApi::class)

package com.hulkdx.findprofessional.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.hulkdx.findprofessional.core.navigation.AndroidNavigationScreen
import com.hulkdx.findprofessional.feature.authentication.login.LoginNavigationScreen
import com.hulkdx.findprofessional.feature.home.HomeNavigationScreen
import org.koin.compose.getKoin
import org.koin.compose.koinInject

@Composable
fun NavigationComposable() {
    val navController = rememberAnimatedNavController()
    CreateScreens(navController)
    SetupNavigation(navController)
}

@Composable
private fun CreateScreens(navController: NavHostController) {
    val startDestination = HomeNavigationScreen().route
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

    navigator.screenState.value?.apply {
        navController.navigate(route)
    }
}
