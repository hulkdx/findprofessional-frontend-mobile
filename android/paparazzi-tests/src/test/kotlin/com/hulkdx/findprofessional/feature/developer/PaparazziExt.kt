package com.hulkdx.findprofessional.feature.developer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import app.cash.paparazzi.Paparazzi
import com.hulkdx.findprofessional.core.theme.AppTheme

internal fun Paparazzi.paparazziTest(content: @Composable () -> Unit) {
    snapshot {
        CompositionLocalProvider(LocalInspectionMode provides true) {
            AppTheme {
                content()
            }
        }
    }
}
