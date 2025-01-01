package com.hulkdx.findprofessional.core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
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

val h1 @Composable get() = LocalAppTypography.current.h1
val h1Medium @Composable get() = LocalAppTypography.current.h1Medium
val h1SemiBold @Composable get() = LocalAppTypography.current.h1SemiBold

val h2 @Composable get() = LocalAppTypography.current.h2
val h2Medium @Composable get() = LocalAppTypography.current.h2Medium

val h3 @Composable get() = LocalAppTypography.current.h3
val h3Medium @Composable get() = LocalAppTypography.current.h3Medium

val body1 @Composable get() = LocalAppTypography.current.body1
val body1Medium @Composable get() = LocalAppTypography.current.body1Medium

val body2 @Composable get() = LocalAppTypography.current.body2
val body2Medium @Composable get() = LocalAppTypography.current.body2Medium
val body2SemiBold @Composable get() = LocalAppTypography.current.body2SemiBold
val body2Bold @Composable get() = LocalAppTypography.current.body2Bold

val body3 @Composable get() = LocalAppTypography.current.body3
val body3Medium @Composable get() = LocalAppTypography.current.body3Medium
val body3Bold @Composable get() = LocalAppTypography.current.body3Bold
val body3SemiBold @Composable get() = LocalAppTypography.current.body3SemiBold
