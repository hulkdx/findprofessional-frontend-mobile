package com.hulkdx.findprofessional.tools.screenshot.tests

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import com.hulkdx.findprofessional.core.ui.theme.AppTheme

fun screenShotTests(
    className: String,
    methodName: String,
    content: @Composable () -> Unit,
) {
    platformScreenShotTests(className, methodName) {
        CompositionLocalProvider(LocalInspectionMode provides true) {
            AppTheme {
                content()
            }
        }
    }
}

expect fun platformScreenShotTests(
    className: String,
    methodName: String,
    content: @Composable () -> Unit,
)
