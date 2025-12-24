package com.hulkdx.findprofessional.tools.screenshot.tests

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import com.hulkdx.findprofessional.app.di.module.getAppNavBarsList
import com.hulkdx.findprofessional.app.di.module.getProNavBarsList
import com.hulkdx.findprofessional.core.ui.commonui.navbar.AppNavBars
import com.hulkdx.findprofessional.core.ui.commonui.navbar.ProAppNavBars
import com.hulkdx.findprofessional.core.ui.theme.AppTheme
import com.hulkdx.findprofessional.core.utils.LocalDebugAppNavBars
import com.hulkdx.findprofessional.core.utils.LocalDebugProAppNavBars

fun screenShotTests(
    className: String,
    methodName: String,
    content: @Composable () -> Unit,
) {
    platformScreenShotTests(className, methodName) {
        CompositionLocalProvider(
            LocalInspectionMode provides true,
            LocalDebugAppNavBars provides AppNavBars(getAppNavBarsList()),
            LocalDebugProAppNavBars provides ProAppNavBars(getProNavBarsList()),
        ) {
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
