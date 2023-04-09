package com.hulkdx.findprofessional

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hulkdx.findprofessional.core.theme.AppTheme
import com.hulkdx.findprofessional.navigation.NavigationComposable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ComposeTransparentStatusBarColor()

            AppTheme {
                NavigationComposable()
            }
        }
    }
}

@Composable
private fun ComposeTransparentStatusBarColor() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()

    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setStatusBarColor(Color.Transparent, useDarkIcons)
        onDispose {}
    }
}
