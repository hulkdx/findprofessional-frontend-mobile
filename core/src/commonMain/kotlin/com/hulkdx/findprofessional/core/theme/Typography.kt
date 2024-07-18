package com.hulkdx.findprofessional.core.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.inter_black
import com.hulkdx.findprofessional.core.resources.inter_bold
import com.hulkdx.findprofessional.core.resources.inter_extrabold
import com.hulkdx.findprofessional.core.resources.inter_extralight
import com.hulkdx.findprofessional.core.resources.inter_light
import com.hulkdx.findprofessional.core.resources.inter_medium
import com.hulkdx.findprofessional.core.resources.inter_regular
import com.hulkdx.findprofessional.core.resources.inter_semibold
import com.hulkdx.findprofessional.core.resources.inter_thin
import org.jetbrains.compose.resources.Font


val interFamily
    @Composable get() = FontFamily(
        Font(Res.font.inter_thin, FontWeight.Thin),
        Font(Res.font.inter_extralight, FontWeight.ExtraLight),
        Font(Res.font.inter_light, FontWeight.Light),
        Font(Res.font.inter_regular, FontWeight.Normal),
        Font(Res.font.inter_medium, FontWeight.Medium),
        Font(Res.font.inter_semibold, FontWeight.SemiBold),
        Font(Res.font.inter_bold, FontWeight.Bold),
        Font(Res.font.inter_extrabold, FontWeight.ExtraBold),
        Font(Res.font.inter_black, FontWeight.Black),
    )

val h1
    @Composable get() = TextStyle(
        fontFamily = interFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 30.sp,
    )

val h1Medium @Composable get() = h1.copy(fontWeight = FontWeight.Medium)
val h1SemiBold @Composable get() = h1.copy(fontWeight = FontWeight.SemiBold)

val h2
    @Composable get() = TextStyle(
        fontFamily = interFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 24.sp,
    )

val h2Medium @Composable get() = h2.copy(fontWeight = FontWeight.Medium)

val h3
    @Composable get() = TextStyle(
        fontFamily = interFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 24.sp,
    )

val h3Medium @Composable get() = h3.copy(fontWeight = FontWeight.Medium)

val body1
    @Composable get() = TextStyle(
        fontFamily = interFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.sp,
    )

val body1Medium @Composable get() = body1.copy(fontWeight = FontWeight.Medium)

val body2
    @Composable get() = TextStyle(
        fontFamily = interFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 17.sp,
    )

val body2Medium @Composable get() = body2.copy(fontWeight = FontWeight.Medium)
val body2SemiBold @Composable get() = body2.copy(fontWeight = FontWeight.SemiBold)
val body2Bold @Composable get() = body2.copy(fontWeight = FontWeight.Bold)

val body3
    @Composable get() = TextStyle(
        fontFamily = interFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    )

val body3Medium @Composable get() = body3.copy(fontWeight = FontWeight.Medium)

val body3Bold @Composable get() = body3.copy(fontWeight = FontWeight.Bold)

val body3SemiBold @Composable get() = body3.copy(fontWeight = FontWeight.SemiBold)
