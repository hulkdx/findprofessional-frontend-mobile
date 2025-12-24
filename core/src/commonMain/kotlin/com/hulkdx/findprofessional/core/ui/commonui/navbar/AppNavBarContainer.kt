package com.hulkdx.findprofessional.core.ui.commonui.navbar

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.ui.commonui.CUSnackBarDurationDefault
import com.hulkdx.findprofessional.core.ui.commonui.navbar.NavData.Icon
import com.hulkdx.findprofessional.core.ui.commonui.navbar.NavData.Text
import com.hulkdx.findprofessional.core.utils.getAppNavBars
import com.hulkdx.findprofessional.core.utils.getNavigator
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

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
    content: @Composable BoxScope.() -> Unit,
) {
    val navBars = getAppNavBars()
    val navigator = getNavigator()

    val items = remember(navBars, navigator) {
        navBars.items.map {
            NavData.create(
                text = Text.Resource(it.text),
                icon = Icon.DrawableResourceIcon(requireNotNull(it.icon)),
                screen = it.screen,
                navigator.getCurrentScreen(),
                navigator,
            )
        }
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
