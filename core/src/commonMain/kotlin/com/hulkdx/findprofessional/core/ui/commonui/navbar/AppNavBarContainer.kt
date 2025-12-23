package com.hulkdx.findprofessional.core.ui.commonui.navbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.bookings
import com.hulkdx.findprofessional.core.resources.explorer
import com.hulkdx.findprofessional.core.resources.ic_nav_bookings
import com.hulkdx.findprofessional.core.resources.ic_nav_explorer
import com.hulkdx.findprofessional.core.resources.ic_nav_profile
import com.hulkdx.findprofessional.core.resources.profile
import com.hulkdx.findprofessional.core.ui.commonui.CUSnackBarDurationDefault
import com.hulkdx.findprofessional.core.utils.getNavigator
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

enum class NavigationBars(
    val text: StringResource,
    val icon: DrawableResource,
    val screen: NavigationScreen,
) {
    EXPLORER(
        text = Res.string.explorer,
        icon = Res.drawable.ic_nav_explorer,
        screen = NavigationScreen.Home()
    ),
    BOOKINGS(
        text = Res.string.bookings,
        icon = Res.drawable.ic_nav_bookings,
        screen = NavigationScreen.MyBookings
    ),
    PROFILE(
        text = Res.string.profile,
        icon = Res.drawable.ic_nav_profile,
        screen = NavigationScreen.Profile
    )
}

@Composable
fun AppNavBarContainer(
    modifier: Modifier = Modifier,
    error: String?,
    onErrorDismissed: () -> Unit,
    errorDurationMillis: Long? = CUSnackBarDurationDefault,
    content: @Composable () -> Unit,
) {
    val navigator = getNavigator()
    val currentScreen = navigator.getCurrentScreen()

    val items = NavigationBars.entries.map {
        NavData.create(
            text = stringResource(it.text),
            icon = painterResource(it.icon),
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
