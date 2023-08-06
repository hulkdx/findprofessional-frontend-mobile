package com.hulkdx.findprofessional.feature.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalInspectionMode
import com.hulkdx.findprofessional.common.navigation.NavigationScreen
import com.hulkdx.findprofessional.common.navigation.Navigator
import org.koin.compose.koinInject


@Composable
fun getNavigator(): Navigator {
    val isPreview = LocalInspectionMode.current
    return if (isPreview) {
        StubNavigator()
    } else {
        koinInject()
    }
}


private class StubNavigator : Navigator {
    override fun navigate(screen: NavigationScreen) {}
    override fun navigate(screen: NavigationScreen, popTo: NavigationScreen, inclusive: Boolean) {}
    override fun goBack() {}
    override fun getCurrentScreen(): NavigationScreen {
        return NavigationScreen.Login
    }
}
