package com.hulkdx.findprofessional.core.navigation

import com.hulkdx.findprofessional.core.features.pro.model.Professional
import com.hulkdx.findprofessional.core.features.pro.model.ProfessionalAvailability
import com.hulkdx.findprofessional.core.features.pro.model.request.SignUpProRequest
import com.hulkdx.findprofessional.core.utils.StringOrRes
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
sealed class NavigationScreen {
    @Serializable data object Splash : NavigationScreen()
    @Serializable data object Login : NavigationScreen()
    @Serializable data object SignUp : NavigationScreen()
    @Serializable data class Home(val message: StringOrRes? = null) : NavigationScreen()
    @Serializable data class HomeDetail(val professional: Professional) : NavigationScreen()
    @Serializable data class Review(val professional: Professional) : NavigationScreen()
    @Serializable data class BookingTime(val professional: Professional) : NavigationScreen()
    @Serializable data class BookingSummery(val professional: Professional, val times: List<ProfessionalAvailability>) : NavigationScreen()
    @Serializable data object Developer : NavigationScreen()
    @Serializable data object Profile : NavigationScreen()
    @Serializable data object ProfileEdit : NavigationScreen()
    @Serializable data object MyBookings : NavigationScreen()

    @Serializable data class ProSignUp(val uiState: SignUpProRequest = SignUpProRequest()): NavigationScreen()
    @Serializable data object ProSchedule: NavigationScreen()
    @Serializable data object ProAvailability: NavigationScreen()
    @Serializable data class ProAvailabilityDetail(val date: LocalDate): NavigationScreen()
    @Serializable data object ProProfile: NavigationScreen()
    @Serializable data object EditProProfile: NavigationScreen()
}
// @formatter:on
