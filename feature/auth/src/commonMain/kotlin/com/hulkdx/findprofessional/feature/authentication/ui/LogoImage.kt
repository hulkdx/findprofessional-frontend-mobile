package com.hulkdx.findprofessional.feature.authentication.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.logo
import org.jetbrains.compose.resources.painterResource

@Composable
fun LogoImage(
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = modifier,
        painter = painterResource(Res.drawable.logo),
        contentDescription = "logo",
    )
}
