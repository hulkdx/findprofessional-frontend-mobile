package com.hulkdx.findprofessional.core.ui.commonui.navbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import com.hulkdx.findprofessional.core.utils.getNavigator
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

data class ProAppNavBars(
    val items: List<NavBarsItem>,
)

@Composable
fun ProAppNavBarContainer(
    modifier: Modifier = Modifier,
    hasStatusBarPadding: Boolean = true,
    error: String?,
    onErrorDismissed: () -> Unit,
    content: @Composable () -> Unit,
) {
    val navBars = koinInject<ProAppNavBars>()
    val navigator = getNavigator()
    val currentScreen = navigator.getCurrentScreen()

    val items = navBars.items.map {
        NavData.create(
            text = stringResource(it.text),
            icon = rememberVectorPainter(requireNotNull(it.iconVector)),
            screen = it.screen,
            currentScreen,
            navigator,
        )
    }

    AppNavBarContainerInternal(
        modifier, hasStatusBarPadding, error, items, onErrorDismissed, content
    )
}
