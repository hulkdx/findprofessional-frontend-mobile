package com.hulkdx.findprofessional.core.navigation

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
abstract class NavigationScreen {
    @Serializable data object Splash : NavigationScreen()
    @Serializable data object Login : NavigationScreen()
    @Serializable data object SignUp : NavigationScreen()
    @Serializable data object Developer : NavigationScreen()
    @Serializable data object Profile : NavigationScreen()
    @Serializable data object ProfileEdit : NavigationScreen()
    @Serializable data object MyBookings : NavigationScreen()

    @Serializable data object ProSchedule: NavigationScreen()
    @Serializable data object ProAvailability: NavigationScreen()
    @Serializable data class ProAvailabilityDetail(val date: LocalDate): NavigationScreen()
    @Serializable data object ProProfile: NavigationScreen()
    @Serializable data object EditProProfile: NavigationScreen()
}
// @formatter:on
