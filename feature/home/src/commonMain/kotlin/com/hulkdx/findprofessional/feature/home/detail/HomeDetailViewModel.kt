package com.hulkdx.findprofessional.feature.home.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.core.features.pro.model.Professional
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.feature.home.detail.availability.HomeDetailAvailabilityUseCase
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.stateIn


class HomeDetailViewModel(
    private val professional: Professional,
    private val navigator: Navigator,
    private val availabilityUseCase: HomeDetailAvailabilityUseCase,
) : ViewModel() {

    val availability = availabilityUseCase.getAvailabilityData(professional)
        .stateIn(viewModelScope, WhileSubscribed(5_000), null)

    fun onLikeClick() {
    }

    fun onChatClick() {
    }

    fun onBookClick() {
        navigator.navigate(NavigationScreen.BookingTime(professional))
    }

    fun onShowMoreReviewClick() {
        navigator.navigate(NavigationScreen.Review(professional))
    }

    fun availabilityMonthMinusOne() {
        availabilityUseCase.monthMinusOne()
    }

    fun availabilityMonthPlusOne() {
        availabilityUseCase.monthPlusOne()
    }
}
