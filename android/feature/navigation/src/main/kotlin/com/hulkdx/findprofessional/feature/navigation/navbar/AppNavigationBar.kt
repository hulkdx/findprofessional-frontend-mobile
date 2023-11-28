package com.hulkdx.findprofessional.feature.navigation.navbar

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.hulkdx.findprofessional.common.navigation.NavigationScreen
import com.hulkdx.findprofessional.common.navigation.Navigator
import com.hulkdx.findprofessional.feature.navigation.getNavigator
import com.hulkdx.findprofessional.resources.MR

internal data class NavData(
    val text: String,
    val icon: ImageVector,
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
            text = stringResource(MR.strings.home.resourceId),
            icon = Icons.Filled.Home,
            selected = currentScreen == NavigationScreen.Home,
            onClick = { navigator.navigate(NavigationScreen.Home) }
        ),
        NavData(
            text = stringResource(MR.strings.profile.resourceId),
            icon = Icons.Filled.Person,
            selected = currentScreen == NavigationScreen.Profile,
            onClick = { navigator.navigate(NavigationScreen.Profile) }
        )
    )

    AppNavigationBarInternal(modifier, items)
}
