package com.hulkdx.findprofessional.feature.navigation.navbar

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hulkdx.findprofessional.common.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.feature.navigation.getNavigator
import com.hulkdx.findprofessional.resources.MR

@Composable
fun BoxScope.AppNavigationBar(modifier: Modifier = Modifier) {
    AppNavigationBar(
        modifier = modifier.align(Alignment.BottomCenter),
    )
}

@Composable
fun AppNavigationBar(
    modifier: Modifier,
) {
    val navigator = getNavigator()
    val items = listOf(
        NavData(
            name = stringResource(MR.strings.home.resourceId),
            icon = Icons.Filled.Home,
            screen = NavigationScreen.Home
        ),
        NavData(
            name = stringResource(MR.strings.profile.resourceId),
            icon = Icons.Filled.Person,
            screen = NavigationScreen.Profile,
        )
    )

    val currentScreen = navigator.getCurrentScreen()
    NavigationBar(modifier = modifier) {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.name) },
                label = { Text(item.name) },
                selected = item.screen == currentScreen,
                onClick = { navigator.navigate(item.screen) },
            )
        }
    }
}

private data class NavData(
    val name: String,
    val icon: ImageVector,
    val screen: NavigationScreen,
)

@Preview
@Composable
private fun AppNavigationBarPreview() {
    AppTheme {
        AppNavigationBar(
            Modifier,
        )
    }
}
