package com.hulkdx.findprofessional.core.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hulkdx.findprofessional.core.R

// region Fonts

val interFamily = FontFamily(
    Font(R.font.inter_thin, FontWeight.Thin),
    Font(R.font.inter_extralight, FontWeight.ExtraLight),
    Font(R.font.inter_light, FontWeight.Light),
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_semibold, FontWeight.SemiBold),
    Font(R.font.inter_bold, FontWeight.Bold),
    Font(R.font.inter_extrabold, FontWeight.ExtraBold),
    Font(R.font.inter_black, FontWeight.Black),
)

// endregion

val h1 = TextStyle(
    fontFamily = interFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 24.sp,
    lineHeight = 30.sp,
)

val h2 = TextStyle(
    fontFamily = interFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 20.sp,
    lineHeight = 24.sp,
)

val h2Medium = h2.copy(fontWeight = FontWeight.Medium)

val h3 = TextStyle(
    fontFamily = interFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 18.sp,
    lineHeight = 24.sp,
)

val body1 = TextStyle(
    fontFamily = interFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 20.sp,
)

val body1Medium = body1.copy(fontWeight = FontWeight.Medium)

val body2 = TextStyle(
    fontFamily = interFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 17.sp,
)

val body2Medium = body2.copy(fontWeight = FontWeight.Medium)

val body2Bold = body2.copy(fontWeight = FontWeight.Bold)

val body3 = TextStyle(
    fontFamily = interFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
)

val body3Medium = body3.copy(fontWeight = FontWeight.Medium)

val body3Bold = body3.copy(fontWeight = FontWeight.Bold)

val body3SemiBold = body3.copy(fontWeight = FontWeight.SemiBold)
