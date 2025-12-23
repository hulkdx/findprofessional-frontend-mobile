package com.hulkdx.findprofessional.feature.profile

import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import kotlinx.serialization.Serializable

object ProfileNavigationScreen {
    @Serializable
    data object Main : NavigationScreen()

    @Serializable
    data object Edit : NavigationScreen()
}
