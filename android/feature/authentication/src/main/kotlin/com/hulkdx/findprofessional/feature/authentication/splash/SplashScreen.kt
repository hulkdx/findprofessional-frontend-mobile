package com.hulkdx.findprofessional.feature.authentication.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hulkdx.findprofessional.common.theme.AppTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun SplashScreen(viewModel: SplashViewModel = getViewModel()) {
    Splash()
}

@Composable
private fun Splash() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.wrapContentSize(),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
@Preview
private fun SplashScreenPreview() {
    AppTheme {
        Splash()
    }
}
