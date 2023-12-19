@file:Suppress("PrivatePropertyName")

package com.hulkdx.findprofessional.core.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private val Green = Color(0xFF00B262)
private val White = Color(0xFFFFFFFF)
private val LightGray = Color(0xFFAEAEB6)
private val DarkWhite = Color(0xFFF5F6FA)
private val YellowStar = Color(0xFFF2A842)

val TextButtonColor = Color(0xFF09B0B9)

internal val lightColorPalette = lightColorScheme(
    primary = Green,
    onPrimary = DarkWhite,
    onSurfaceVariant = LightGray,
    surfaceVariant = White,
    scrim = YellowStar,
)

internal val darkColorPalette = lightColorPalette
