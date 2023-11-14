package com.hulkdx.findprofessional.feature.authentication.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.feature.authentication.ui.LogoImage
import org.koin.androidx.compose.getViewModel

@Composable
fun SplashScreen(viewModel: SplashViewModel = getViewModel()) {
    Splash()
}

@Composable
private fun Splash() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6FA)),
        contentAlignment = Alignment.Center
    ) {
        LogoImage()
    }
}

@Composable
@Preview
private fun SplashScreenPreview() {
    AppTheme {
        Splash()
    }
}
