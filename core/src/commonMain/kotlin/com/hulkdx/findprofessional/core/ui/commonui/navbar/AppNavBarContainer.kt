package com.hulkdx.findprofessional.core.ui.commonui.navbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalInspectionMode
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.ui.commonui.CUSnackBarDurationDefault
import com.hulkdx.findprofessional.core.utils.getAppNavBars
import com.hulkdx.findprofessional.core.utils.getNavigator
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

data class AppNavBars(
    val items: List<NavBarsItem>,
)

data class NavBarsItem(
    val text: StringResource,
    val icon: DrawableResource? = null,
    val iconVector: ImageVector? = null,
    val screen: NavigationScreen,
)

@Composable
fun AppNavBarContainer(
    modifier: Modifier = Modifier,
    error: String?,
    onErrorDismissed: () -> Unit,
    errorDurationMillis: Long? = CUSnackBarDurationDefault,
    content: @Composable () -> Unit,
) {
    val navBars = getAppNavBars()
    val navigator = getNavigator()
    val currentScreen = navigator.getCurrentScreen()

    val items = navBars.items.map {
        NavData.create(
            text = stringResource(it.text),
            icon = painterResource(requireNotNull(it.icon)),
            screen = it.screen,
            currentScreen,
            navigator,
        )
    }

    AppNavBarContainerInternal(
        modifier = modifier,
        error = error,
        items = items,
        onErrorDismissed = onErrorDismissed,
        content = content,
        errorDurationMillis = errorDurationMillis,
    )
}
