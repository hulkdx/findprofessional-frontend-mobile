package com.hulkdx.findprofessional.core.commonui.navbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.explorer
import com.hulkdx.findprofessional.core.resources.ic_nav_explorer
import com.hulkdx.findprofessional.core.resources.ic_nav_profile
import com.hulkdx.findprofessional.core.resources.profile
import com.hulkdx.findprofessional.core.utils.getNavigator
import org.jetbrains.compose.resources.stringResource

@Composable
fun AppNavBarContainer(
    modifier: Modifier = Modifier,
    error: String?,
    onErrorDismissed: () -> Unit,
    content: @Composable () -> Unit,
) {
    val navigator = getNavigator()
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

    AppNavBarContainerInternal(modifier, error, items, onErrorDismissed, content)
}
