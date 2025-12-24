package com.hulkdx.findprofessional.feature.booking

import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.feature.pro.model.Professional
import com.hulkdx.findprofessional.feature.pro.model.ProfessionalAvailability
import kotlinx.serialization.Serializable

object BookingNavigationScreen {
    @Serializable
    data class Time(val professional: Professional) : NavigationScreen

    @Serializable
    data class Summery(
        val professional: Professional,
        val times: List<ProfessionalAvailability>
    ) : NavigationScreen
}
