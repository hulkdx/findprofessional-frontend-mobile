package com.hulkdx.findprofessional.feature.review

import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.feature.pro.model.Professional
import kotlinx.serialization.Serializable

object ReviewNavigationScreen {
    @Serializable
    data class Main(val professional: Professional) : NavigationScreen
}
