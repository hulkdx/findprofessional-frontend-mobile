package com.hulkdx.findprofessional.navigation

import androidx.navigation.NavController
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.navigation.Navigator
import java.lang.ref.WeakReference


class NavigatorImpl: Navigator {

    private var navController: WeakReference<NavController>? = null

    override fun navigate(screen: NavigationScreen) {
        navController?.get()?.navigate(screen.route)
    }

    fun setNavController(navController: NavController) {
        this.navController = WeakReference(navController)
    }
}
