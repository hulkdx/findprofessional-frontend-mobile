package com.hulkdx.findprofessional.feature.navigation.navbar

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hulkdx.findprofessional.common.navigation.NavigationScreen
import com.hulkdx.findprofessional.common.navigation.Navigator
import com.hulkdx.findprofessional.common.resources.Res
import com.hulkdx.findprofessional.common.resources.explorer
import com.hulkdx.findprofessional.common.resources.profile
import com.hulkdx.findprofessional.core.R
import com.hulkdx.findprofessional.feature.navigation.getNavigator
import org.jetbrains.compose.resources.stringResource

internal data class NavData(
    val text: String,
    val icon: Int,
    val selected: Boolean,
    val onClick: () -> Unit,
)

@Composable
fun BoxScope.AppNavigationBar(modifier: Modifier = Modifier) {
    AppNavigationBar(
        modifier = modifier.align(Alignment.BottomCenter),
        navigator = getNavigator(),
    )
}

@Composable
fun AppNavigationBar(
    modifier: Modifier = Modifier,
    navigator: Navigator = getNavigator(),
) {
    val currentScreen = navigator.getCurrentScreen()

    val items = listOf(
        NavData(
            text = stringResource(Res.string.explorer),
            icon = R.drawable.ic_nav_explorer,
            selected = currentScreen == NavigationScreen.Home,
            onClick = { navigator.navigate(NavigationScreen.Home) }
        ),
        NavData(
            text = stringResource(Res.string.profile),
            icon = R.drawable.ic_nav_profile,
            selected = currentScreen == NavigationScreen.Profile,
            onClick = { navigator.navigate(NavigationScreen.Profile) }
        )
    )

    AppNavigationBarInternal(modifier, items)
}
