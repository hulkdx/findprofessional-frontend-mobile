package com.hulkdx.findprofessional.feature.home

import com.hulkdx.findprofessional.feature.pro.model.Professional
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.utils.StringOrRes
import kotlinx.serialization.Serializable

object HomeNavigationScreen {
    @Serializable
    data class Home(val message: StringOrRes? = null) : NavigationScreen

    @Serializable
    data class HomeDetail(val professional: Professional) : NavigationScreen
}
