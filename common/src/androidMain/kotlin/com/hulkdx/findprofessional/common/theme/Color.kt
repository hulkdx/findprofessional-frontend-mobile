@file:Suppress("PrivatePropertyName")

package com.hulkdx.findprofessional.common.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private val Green = Color(0xFF0F6A2C)
private val LightGreen = Color(0xFFD4E0D8)
private val White = Color(0xFFFFFFFF)
private val LightBlue = Color(0xFFE1EFFF)
private val LightGrey = Color(0xFFF5F6F9)
private val Gray = Color(0xFF8B8B8B)
private val Yellow = Color(0xFFF6C548)

internal val lightColorPalette = lightColorScheme(
    primary = Green,
    onPrimary = White,
    onSecondary = LightBlue,
    secondaryContainer = LightGreen,
    onTertiary = LightGrey,
    onTertiaryContainer = Gray,
    scrim = Yellow,
)

internal val darkColorPalette = lightColorPalette
