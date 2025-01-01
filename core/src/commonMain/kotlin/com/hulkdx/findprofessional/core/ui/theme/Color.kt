@file:Suppress("PrivatePropertyName")

package com.hulkdx.findprofessional.core.ui.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private val Green = Color(0xFF00B262)
private val DarkGreen = Color(0xFF006B3B)
private val White = Color(0xFFFFFFFF)
private val LightGray = Color(0xFFAEAEB6)
private val DarkWhite = Color(0xFFF5F6FA)
private val YellowStar = Color(0xFFF2A842)
private val Red = Color(0xFFFF4538)

private val SelectedRed = Color(0xFFD80000)
private val UnselectedGray = Color(0xFFCAC9DA)

private val TextButtonColor = Color(0xFF09B0B9)
private val TextColorNormal = Color(0xFF32313F)
private val TextColorGray = Color(0xFF9D9CAC)

private val CalendarSelectedColor = Color(0xFF33C181)

private val BorderColor = Color(0xFFE6EAEE)

internal val lightColorPalette = lightColorScheme(
    primary = Green,
    onPrimary = DarkWhite,
    secondary = Red,
    tertiary = DarkGreen,
    onTertiary = BorderColor,
    onSurfaceVariant = LightGray,
    surfaceVariant = White,
    scrim = YellowStar,
    error = SelectedRed,
    onError = UnselectedGray,
    outlineVariant = CalendarSelectedColor,
    outline = TextColorNormal,
    onErrorContainer = TextButtonColor,
    errorContainer = TextColorGray,
)

internal val darkColorPalette = lightColorPalette
