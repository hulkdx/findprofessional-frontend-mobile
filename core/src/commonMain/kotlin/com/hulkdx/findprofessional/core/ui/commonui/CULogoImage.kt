package com.hulkdx.findprofessional.core.ui.commonui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.logo
import org.jetbrains.compose.resources.painterResource

@Composable
fun CULogoImage(
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = modifier,
        painter = painterResource(Res.drawable.logo),
        contentDescription = "logo",
    )
}
