package com.hulkdx.findprofessional.feature.authentication.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hulkdx.findprofessional.core.commonui.CULogoImage
import com.hulkdx.findprofessional.core.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel

@Suppress("UNUSED_PARAMETER")
@Composable
fun SplashScreen(viewModel: SplashViewModel = koinViewModel()) {
    Splash()
}

@Composable
fun Splash() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary),
        contentAlignment = Alignment.Center
    ) {
        CULogoImage()
    }
}

@Composable
@Preview
private fun SplashScreenPreview() {
    AppTheme {
        Splash()
    }
}
