package com.hulkdx.findprofessional.core.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hulkdx.findprofessional.core.R

// region Fonts

val interFamily = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_bold, FontWeight.Bold),
    Font(R.font.inter_light, FontWeight.Light),
    Font(R.font.inter_medium, FontWeight.Medium),
)

// endregion

val h1 = TextStyle(
    fontFamily = interFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 38.sp,
    lineHeight = 42.sp,
)

val h3 = TextStyle(
    fontFamily = interFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 17.sp,
    lineHeight = 20.sp,
)

val body1 = TextStyle(
    fontFamily = interFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 23.sp,
)

val body2 = TextStyle(
    fontFamily = interFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
)
