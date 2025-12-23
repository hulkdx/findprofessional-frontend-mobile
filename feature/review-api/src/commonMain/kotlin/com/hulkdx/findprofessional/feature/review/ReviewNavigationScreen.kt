package com.hulkdx.findprofessional.feature.review

import com.hulkdx.findprofessional.feature.pro.model.Professional
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import kotlinx.serialization.Serializable

object ReviewNavigationScreen {
    @Serializable
    data class Review(val professional: Professional) : NavigationScreen()
}
