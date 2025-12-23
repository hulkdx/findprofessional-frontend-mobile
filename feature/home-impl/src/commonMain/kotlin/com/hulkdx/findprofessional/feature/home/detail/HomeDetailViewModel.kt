package com.hulkdx.findprofessional.feature.home.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.feature.booking.BookingNavigationScreen
import com.hulkdx.findprofessional.feature.home.detail.availability.HomeDetailAvailabilityUseCase
import com.hulkdx.findprofessional.feature.pro.model.Professional
import com.hulkdx.findprofessional.feature.review.ReviewNavigationScreen
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
        navigator.navigate(BookingNavigationScreen.Time(professional))
    }

    fun onShowMoreReviewClick() {
        navigator.navigate(ReviewNavigationScreen.Main(professional))
    }

    fun availabilityMonthMinusOne() {
        availabilityUseCase.monthMinusOne()
    }

    fun availabilityMonthPlusOne() {
        availabilityUseCase.monthPlusOne()
    }
}
