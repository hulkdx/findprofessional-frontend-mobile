package com.hulkdx.findprofessional.feature.booking.confirmation

import androidx.lifecycle.ViewModel
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.feature.home.HomeNavigationScreen

class BookingConfirmationViewModel(
    private val navigator: Navigator,
) : ViewModel() {

    fun onCloseClicked() {
        navigator.navigate(
            screen = HomeNavigationScreen.Home,
            popTo = HomeNavigationScreen.Home,
            inclusive = true,
        )
    }

}
