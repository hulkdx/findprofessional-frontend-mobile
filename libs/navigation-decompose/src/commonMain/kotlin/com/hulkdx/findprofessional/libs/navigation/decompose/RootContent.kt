package com.hulkdx.findprofessional.libs.navigation.decompose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import org.koin.compose.koinInject

@Composable
fun RootContent(
    component: RootComponent,
    screenContent: @Composable (NavigationScreen) -> Unit,
) {
    Children(
        stack = component.stack,
        animation = backAnimation(component.backHandler, component::onBackClicked),
    ) {
        val (child, viewModelStoreOwner) = it.instance
        CompositionLocalProvider(LocalViewModelStoreOwner provides viewModelStoreOwner) {
            ChildrenContent(child, screenContent)
        }
    }
}

@Composable
private fun ChildrenContent(
    child: NavigationScreen,
    screenContent: @Composable (NavigationScreen) -> Unit,
) {
    SetupCurrentScreenNavigator(child)
    screenContent(child)
}

@Composable
private fun SetupCurrentScreenNavigator(
    child: NavigationScreen,
    navigator: DecomposeNavigator = koinInject(),
) {
    navigator.setCurrentScreen(child)
}
