package com.hulkdx.findprofessional.core.commonui.navbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.explorer
import com.hulkdx.findprofessional.core.resources.ic_nav_explorer
import com.hulkdx.findprofessional.core.resources.ic_nav_profile
import com.hulkdx.findprofessional.core.resources.profile
import org.jetbrains.compose.resources.stringResource

@Composable
fun AppNavigationBar(
    modifier: Modifier = Modifier,
    navigator: Navigator = getNavigator(),
) {
    val currentScreen = navigator.getCurrentScreen()

    val items = listOf(
        NavData.create(
            text = stringResource(Res.string.explorer),
            icon = Res.drawable.ic_nav_explorer,
            screen = NavigationScreen.Home,
            currentScreen,
            navigator,
        ),
        NavData.create(
            text = stringResource(Res.string.profile),
            icon = Res.drawable.ic_nav_profile,
            screen = NavigationScreen.Profile,
            currentScreen,
            navigator,
        )
    )

    AppNavigationBarInternal(modifier, items)
}
