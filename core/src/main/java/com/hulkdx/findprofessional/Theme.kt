package com.hulkdx.findprofessional

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object ThemeColor {
    val background: Color
        get() = MutableThemeColor.background
}

object AppColor {
    val White = Color(0xFFFFFFFF)
    val Black = Color(0xFF000000)
}

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    if (darkTheme) {
        MutableThemeColor.background = AppColor.Black
    } else {
        MutableThemeColor.background = AppColor.White
    }

    content()
}

// region Theme Color Impl

private object MutableThemeColor {
    var background: Color = UndefinedColor
}

private val UndefinedColor = Color(0)

// endregion Theme Color Impl
