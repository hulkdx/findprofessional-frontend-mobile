package com.hulkdx.findprofessional.core.ui.commonui.navbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.availability
import com.hulkdx.findprofessional.core.resources.profile
import com.hulkdx.findprofessional.core.resources.schedule
import com.hulkdx.findprofessional.core.utils.getNavigator
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProAppNavBarContainer(
    modifier: Modifier = Modifier,
    error: String?,
    onErrorDismissed: () -> Unit,
    content: @Composable () -> Unit,
) {
    val navigator = getNavigator()
    val currentScreen = navigator.getCurrentScreen()

    val items = listOf(
        NavData.create(
            text = stringResource(Res.string.schedule),
            icon = rememberVectorPainter(Icons.Filled.Event),
            screen = NavigationScreen.ProSchedule,
            currentScreen,
            navigator,
        ),
        NavData.create(
            text = stringResource(Res.string.availability),
            icon = rememberVectorPainter(Icons.Filled.AccessTime),
            screen = NavigationScreen.ProAvailability,
            currentScreen,
            navigator,
        ),
        NavData.create(
            text = stringResource(Res.string.profile),
            icon = rememberVectorPainter(Icons.Outlined.AccountCircle),
            screen = NavigationScreen.ProProfile,
            currentScreen,
            navigator,
        ),
    )

    AppNavBarContainerInternal(modifier, error, items, onErrorDismissed, content)
}

