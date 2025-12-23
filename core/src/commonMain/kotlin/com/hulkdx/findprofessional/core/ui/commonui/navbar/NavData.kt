package com.hulkdx.findprofessional.core.ui.commonui.navbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.navigation.Navigator
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

internal data class NavData(
    val text: Text,
    val icon: Icon,
    val selected: Boolean,
    val onClick: (() -> Unit)?,
) {
    sealed interface Text {
        data class Normal(val data: String) : Text {
            @Composable
            override fun localized(): String {
                return data
            }
        }

        data class Resource(val data: StringResource) : Text {
            @Composable
            override fun localized(): String {
                return stringResource(data)
            }
        }

        @Composable
        fun localized(): String
    }

    sealed interface Icon {
        data class DrawableResourceIcon(val data: DrawableResource) : Icon {
            @Composable
            override fun localized(): Painter {
                return painterResource(data)
            }
        }

        data class ImageVectorIcon(val data: ImageVector) : Icon {
            @Composable
            override fun localized(): Painter {
                return rememberVectorPainter(data)
            }
        }

        @Composable
        fun localized(): Painter
    }

    companion object {
        fun create(
            text: Text,
            icon: Icon,
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
