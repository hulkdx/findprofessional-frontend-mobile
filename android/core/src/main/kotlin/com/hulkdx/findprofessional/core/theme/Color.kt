@file:Suppress("PrivatePropertyName")

package com.hulkdx.findprofessional.core.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private val Green = Color(0xFF00B262)
private val White = Color(0xFFFFFFFF)
private val LightGray = Color(0xFFAEAEB6)
private val DarkWhite = Color(0xFFF5F6FA)
private val YellowStar = Color(0xFFF2A842)

private val SelectedRed = Color(0xFFD80000)
private val UnselectedGray = Color(0xFFCAC9DA)

val TextButtonColor = Color(0xFF09B0B9)

val CalendarSelectedColor = Color(0xFF00B262)

internal val lightColorPalette = lightColorScheme(
    primary = Green,
    onPrimary = DarkWhite,
    onSurfaceVariant = LightGray,
    surfaceVariant = White,
    scrim = YellowStar,
    error = SelectedRed,
    onError = UnselectedGray,
    outlineVariant = CalendarSelectedColor,
)

internal val darkColorPalette = lightColorPalette
