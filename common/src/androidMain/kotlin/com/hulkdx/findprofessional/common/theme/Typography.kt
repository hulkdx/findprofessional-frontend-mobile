package com.hulkdx.findprofessional.common.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hulkdx.findprofessional.common.R

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
    fontWeight = FontWeight.ExtraBold,
    fontSize = 30.sp,
)

val h3 = TextStyle(
    fontFamily = interFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 17.sp,
    lineHeight = 20.sp,
)

val h3Bold = TextStyle(
    fontFamily = interFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 17.sp,
    lineHeight = 20.sp,
)

val body1 = TextStyle(
    fontFamily = interFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 20.sp,
)

val body1Medium = TextStyle(
    fontFamily = interFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
    lineHeight = 23.sp,
)

val body2 = TextStyle(
    fontFamily = interFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
)

val body2Medium = TextStyle(
    fontFamily = interFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
)

val body2Bold = TextStyle(
    fontFamily = interFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 14.sp,
)

val body3 = TextStyle(
    fontFamily = interFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
)

val body3Bold = TextStyle(
    fontFamily = interFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 12.sp,
)

val body3SemiBold = TextStyle(
    fontFamily = interFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 12.sp,
)
