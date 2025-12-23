package com.hulkdx.findprofessional.feature.pro.profile

import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import kotlinx.serialization.Serializable

object ProProfileNavigationScreen {
    @Serializable
    data object Main : NavigationScreen()
    @Serializable
    data object Edit : NavigationScreen()
}
