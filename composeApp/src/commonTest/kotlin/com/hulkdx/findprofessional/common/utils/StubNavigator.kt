package com.hulkdx.findprofessional.common.utils

import com.hulkdx.findprofessional.common.navigation.NavigationScreen
import com.hulkdx.findprofessional.common.navigation.Navigator

class StubNavigator : Navigator {
    override fun navigate(screen: NavigationScreen) {}

    override fun navigate(
        screen: NavigationScreen,
        popTo: NavigationScreen,
        inclusive: Boolean,
    ) {
    }

    override fun goBack() {}

    override fun getCurrentScreen(): NavigationScreen {
        throw RuntimeException()
    }
}
