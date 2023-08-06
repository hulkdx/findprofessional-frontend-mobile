package com.hulkdx.findprofessional.feature.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalInspectionMode
import com.hulkdx.findprofessional.common.navigation.Navigator
import org.koin.compose.koinInject

@Composable
fun getNavigator(): Navigator {
    return koinInject()
}
