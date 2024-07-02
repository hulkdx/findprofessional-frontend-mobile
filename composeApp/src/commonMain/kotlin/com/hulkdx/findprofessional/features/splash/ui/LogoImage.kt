package com.hulkdx.findprofessional.features.splash.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hulkdx.findprofessional.common.resources.Res
import com.hulkdx.findprofessional.common.resources.logo
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun LogoImage(
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = modifier,
        painter = painterResource(Res.drawable.logo),
        contentDescription = "logo",
    )
}
