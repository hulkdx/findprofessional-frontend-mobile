package com.hulkdx.findprofessional.feature.pro.availability

import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

object ProAvailabilityNavigationScreen {
    @Serializable
    data object Main : NavigationScreen

    @Serializable
    data class Detail(val date: LocalDate) : NavigationScreen
}
