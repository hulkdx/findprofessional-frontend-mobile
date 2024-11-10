package com.hulkdx.findprofessional.core.commonui.navbar

import androidx.compose.ui.graphics.painter.Painter
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.navigation.Navigator
import org.jetbrains.compose.resources.DrawableResource

internal data class NavData(
    val text: String,
    val icon: Painter,
    val selected: Boolean,
    val onClick: (() -> Unit)?,
) {
    companion object {
        fun create(
            text: String,
            icon: Painter,
            screen: NavigationScreen,
            currentScreen: NavigationScreen,
            navigator: Navigator,
        ): NavData {
            return NavData(
                text = text,
                icon = icon,
                selected = currentScreen == screen,
                onClick = createOnClick(screen, currentScreen, navigator)
            )
        }

        private fun createOnClick(
            screen: NavigationScreen,
            currentScreen: NavigationScreen,
            navigator: Navigator,
        ): (() -> Unit)? {
            if (currentScreen == screen) return null
            return {
                navigator.navigate(screen)
            }
        }
    }
}
