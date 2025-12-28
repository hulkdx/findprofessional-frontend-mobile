package com.hulkdx.findprofessional.feature.home

import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.feature.pro.model.Professional
import kotlinx.serialization.Serializable

object HomeNavigationScreen {
    @Serializable
    data object Home : NavigationScreen

    @Serializable
    data class HomeDetail(val professional: Professional) : NavigationScreen
}
