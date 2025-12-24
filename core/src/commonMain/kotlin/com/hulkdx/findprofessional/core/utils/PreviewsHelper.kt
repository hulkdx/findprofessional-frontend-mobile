package com.hulkdx.findprofessional.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalInspectionMode
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.core.platform.isDebug
import com.hulkdx.findprofessional.core.ui.commonui.navbar.AppNavBars
import com.hulkdx.findprofessional.core.ui.commonui.navbar.ProAppNavBars
import org.koin.compose.koinInject
import kotlin.reflect.KClass

/**
 *  A safe function to get navigator for previews
 */
@Composable
fun getNavigator(): Navigator {
    return if (isDebug() && LocalInspectionMode.current) {
        // @formatter:off
        object : Navigator {
            override fun navigate(screen: NavigationScreen) {}
            override fun navigate(screen: NavigationScreen, popTo: NavigationScreen, inclusive: Boolean) {}
            override fun goBack() {}
            override fun getCurrentScreen() = object: NavigationScreen() {}
            override fun goBack(screen: KClass<out NavigationScreen>) {}
        }
        // @formatter:on
    } else {
        koinInject()
    }
}

val LocalDebugAppNavBars = staticCompositionLocalOf { AppNavBars(emptyList()) }

@Composable
fun getAppNavBars(): AppNavBars {
    return if (isDebug() && LocalInspectionMode.current) {
        LocalDebugAppNavBars.current
    } else {
        koinInject()
    }
}

val LocalDebugProAppNavBars = staticCompositionLocalOf { ProAppNavBars(emptyList()) }

@Composable
fun getProAppNavBars(): ProAppNavBars {
    return if (isDebug() && LocalInspectionMode.current) {
        LocalDebugProAppNavBars.current
    } else {
        koinInject()
    }
}
