package com.hulkdx.findprofessional.core.theme

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val LocalAppTypography: ProvidableCompositionLocal<AppTypography> =
    staticCompositionLocalOf { error("LocalTypography was not provided") }

fun appTypography(fontFamily: FontFamily) = AppTypography(
    h1 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 30.sp,
    ),
    h2 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 24.sp,
    ),
    h3 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 24.sp,
    ),
    body1 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.sp,
    ),
    body2 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 17.sp,
    ),
    body3 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),
)

data class AppTypography(
    val h1: TextStyle,
    val h1Medium: TextStyle = h1.copy(fontWeight = FontWeight.Medium),
    val h1SemiBold: TextStyle = h1.copy(fontWeight = FontWeight.SemiBold),
    val h2: TextStyle,
    val h2Medium: TextStyle = h2.copy(fontWeight = FontWeight.Medium),
    val h3: TextStyle,
    val h3Medium: TextStyle = h3.copy(fontWeight = FontWeight.Medium),
    val body1: TextStyle,
    val body1Medium: TextStyle = body1.copy(fontWeight = FontWeight.Medium),
    val body2: TextStyle,
    val body2Medium: TextStyle = body2.copy(fontWeight = FontWeight.Medium),
    val body2SemiBold: TextStyle = body2.copy(fontWeight = FontWeight.SemiBold),
    val body2Bold: TextStyle = body2.copy(fontWeight = FontWeight.Bold),
    val body3: TextStyle,
    val body3Medium: TextStyle = body3.copy(fontWeight = FontWeight.Medium),
    val body3Bold: TextStyle = body3.copy(fontWeight = FontWeight.Bold),
    val body3SemiBold: TextStyle = body3.copy(fontWeight = FontWeight.SemiBold),
)
