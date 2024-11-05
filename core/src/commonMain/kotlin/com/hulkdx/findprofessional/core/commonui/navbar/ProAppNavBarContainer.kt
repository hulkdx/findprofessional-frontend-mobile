package com.hulkdx.findprofessional.core.commonui.navbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.statusBarsPadding
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

@Composable
fun ProAppNavBarContainer(
    modifier: Modifier = Modifier,
    error: String?,
    onErrorDismissed: () -> Unit,
    content: @Composable () -> Unit,
) {
    val navigator = getNavigator()
    val currentScreen = navigator.getCurrentScreen()

    // TODO:
    val items = listOf<NavData>()

    AppNavBarContainerInternal(modifier, error, items, onErrorDismissed, content)
}

