package com.hulkdx.findprofessional.core.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private val Green = Color(0xFF0F6A2C)
private val White = Color(0xFFFFFFFF)
private val LightBlue = Color(0xFFE1EFFF)
private val LightGrey = Color(0xFFF4F4F4)
private val Gray = Color(0xFF8B8B8B)

internal val lightColorPalette = lightColorScheme(
    primary = Green,
    onPrimary = White,
    onSecondary = LightBlue,
    onTertiary = LightGrey,
)

internal val darkColorPalette = lightColorPalette
