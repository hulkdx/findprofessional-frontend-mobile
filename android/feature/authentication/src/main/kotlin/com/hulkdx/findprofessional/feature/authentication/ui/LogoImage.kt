package com.hulkdx.findprofessional.feature.authentication.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.hulkdx.findprofessional.feature.authentication.R

@Composable
fun LogoImage(
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = modifier,
        painter = painterResource(R.drawable.logo),
        contentDescription = "logo",
    )
}
