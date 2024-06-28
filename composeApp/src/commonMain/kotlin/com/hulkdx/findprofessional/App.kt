package com.hulkdx.findprofessional

import androidx.compose.runtime.Composable
import com.hulkdx.findprofessional.core.di.appModule
import com.hulkdx.findprofessional.core.navigation.AppNavigator
import com.hulkdx.findprofessional.core.platform.Context
import com.hulkdx.findprofessional.core.theme.AppTheme
import org.koin.compose.KoinApplication

@Composable
fun App(context: Context) {
    KoinApplication(application = {
        modules(appModule(context))
    }) {
        AppTheme {
            AppNavigator()
        }
    }
}
