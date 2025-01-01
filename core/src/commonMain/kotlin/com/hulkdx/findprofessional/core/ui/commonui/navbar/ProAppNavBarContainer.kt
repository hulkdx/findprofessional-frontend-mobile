package com.hulkdx.findprofessional.core.ui.commonui.navbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hulkdx.findprofessional.core.commonui.CUSnackBar
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.explorer
import com.hulkdx.findprofessional.core.resources.ic_nav_explorer
import com.hulkdx.findprofessional.core.resources.ic_nav_profile
import com.hulkdx.findprofessional.core.resources.profile
import com.hulkdx.findprofessional.core.utils.getNavigator
import org.jetbrains.compose.resources.stringResource
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Person2
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import com.hulkdx.findprofessional.core.resources.availability
import com.hulkdx.findprofessional.core.resources.schedule
import org.jetbrains.compose.resources.painterResource

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

