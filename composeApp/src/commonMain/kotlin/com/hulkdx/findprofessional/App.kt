package com.hulkdx.findprofessional

import androidx.compose.runtime.Composable
import com.hulkdx.findprofessional.core.navigation.AppNavigator
import com.hulkdx.findprofessional.core.theme.AppTheme

@Composable
fun App() {
    AppTheme {
        AppNavigator()
    }
}
