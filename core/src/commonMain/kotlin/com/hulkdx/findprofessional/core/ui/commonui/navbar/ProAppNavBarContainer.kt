package com.hulkdx.findprofessional.core.ui.commonui.navbar

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.hulkdx.findprofessional.core.ui.commonui.navbar.NavData.Icon
import com.hulkdx.findprofessional.core.ui.commonui.navbar.NavData.Text
import com.hulkdx.findprofessional.core.utils.getNavigator
import com.hulkdx.findprofessional.core.utils.getProAppNavBars

data class ProAppNavBars(
    val items: List<NavBarsItem>,
)

@Composable
fun ProAppNavBarContainer(
    modifier: Modifier = Modifier,
    hasStatusBarPadding: Boolean = true,
    error: String?,
    onErrorDismissed: () -> Unit,
    content: @Composable BoxScope.() -> Unit,
) {
    val navBars = getProAppNavBars()
    val navigator = getNavigator()

    val items = remember(navBars, navigator) {
        navBars.items.map {
            NavData.create(
                text = Text.Resource(it.text),
                icon = Icon.ImageVectorIcon(requireNotNull(it.iconVector)),
                screen = it.screen,
                navigator.getCurrentScreen(),
                navigator,
            )
        }
    }

    AppNavBarContainerInternal(
        modifier,
        hasStatusBarPadding,
        error,
        items,
        onErrorDismissed,
        content,
    )
}
