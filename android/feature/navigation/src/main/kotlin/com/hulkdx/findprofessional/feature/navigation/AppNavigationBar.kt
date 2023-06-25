package com.hulkdx.findprofessional.feature.navigation

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.hulkdx.findprofessional.common.navigation.NavigationScreen
import com.hulkdx.findprofessional.common.navigation.Navigator
import org.koin.compose.koinInject

@Composable
fun BoxScope.AppNavigationBar() {
    AppNavigationBar(Modifier.align(Alignment.BottomCenter))
}

@Composable
fun AppNavigationBar(
    modifier: Modifier,
    navigator: Navigator = koinInject(),
) {
    val items = listOf(
        NavData(
            name = "Home",
            icon = Icons.Filled.Home,
            screen = NavigationScreen.Home
        ),
        NavData(
            name = "Settings",
            icon = Icons.Filled.Settings,
            screen = NavigationScreen.Login,
        )
    )

    NavigationBar(modifier = modifier) {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.name) },
                label = { Text(item.name) },
                selected = item.screen == navigator.getCurrentScreen(),
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
