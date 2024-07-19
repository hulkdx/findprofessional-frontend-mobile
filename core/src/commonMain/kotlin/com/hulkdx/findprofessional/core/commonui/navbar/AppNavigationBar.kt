package com.hulkdx.findprofessional.core.commonui.navbar

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.explorer
import com.hulkdx.findprofessional.core.resources.ic_nav_explorer
import com.hulkdx.findprofessional.core.resources.ic_nav_profile
import com.hulkdx.findprofessional.core.resources.profile
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.stringResource

internal data class NavData(
    val text: String,
    val icon: DrawableResource,
    val selected: Boolean,
    val onClick: () -> Unit,
)

@Composable
fun BoxScope.AppNavigationBar(
    modifier: Modifier = Modifier,
    navigator: Navigator = getNavigator(),
) {
    AppNavigationBar(
        modifier = modifier.align(Alignment.BottomCenter),
        navigator = navigator,
    )
}

@Composable
fun AppNavigationBar(
    modifier: Modifier = Modifier,
    navigator: Navigator = getNavigator(),
) {
    val currentScreen = navigator.getCurrentScreen()

    val items = listOf(
        NavData(
            text = stringResource(Res.string.explorer),
            icon = Res.drawable.ic_nav_explorer,
            selected = currentScreen == NavigationScreen.Home,
            onClick = { navigator.navigate(NavigationScreen.Home) }
        ),
        NavData(
            text = stringResource(Res.string.profile),
            icon = Res.drawable.ic_nav_profile,
            selected = currentScreen == NavigationScreen.Profile,
            onClick = { navigator.navigate(NavigationScreen.Profile) }
        )
    )

    AppNavigationBarInternal(modifier, items)
}
